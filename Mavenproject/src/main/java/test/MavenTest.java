package test;

import java.util.ArrayList;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class MavenTest {

    public static void main(String[] args) {
        String host = "http://10.90.131.179:8080";
        String crumb = "61087256e43fd59467d3d07b1c7a4a09";


//      readJob(host, "TestPipe");

        System.out.println("List of created jobs:");
        List<String> jobList = listJobs(host);
        for(String job : jobList) {
            System.out.println(job);
        }

//        System.out.println("Check 1: Delete AA_TEST_JOB1 (created manually)");
//      deleteJob(host, "TestPipe");

        /*System.out.println("Check 2: Create AA_TEST_JOB2 by copying first job");
        copyJob(host, "AA_TEST_JOB2", jobList.get(0));*/
//		deleteJob(host, "AA_TEST_JOB2");

//        System.out.println("Check 3: Create AA_TEST_JOB3 by using a generic xml configuration");
//        createJob(host, "Nyttjobb", "<project><builders/><publishers/><buildWrappers/></project>");
//		deleteJob(host, "AA_TEST_JOB3");

        /*System.out.println("Check 4: Create AA_TEST_JOB4 by using the xml configuration from the first job (similar to copyJob)");
        String configXML = readJob(host, jobList.get(0));
        createJob(host, "AA_TEST_JOB4", configXML);*/
//		deleteJob(host, "AA_TEST_JOB4");


    }

    public static List<String> listJobs(String url) {
        Client client = Client.create();
		client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter("admin1", "admin1"));
        WebResource webResource = client.resource(url + "/api/xml");
        ClientResponse response = webResource.get(ClientResponse.class);
        String jsonResponse = response.getEntity(String.class);
        client.destroy();
//		System.out.println("Response listJobs:::::"+jsonResponse);

        // Assume jobs returned are in xml format, TODO using an XML Parser would be better here
        // Get name from <job><name>...
        List<String> jobList = new ArrayList<String>();
        String[] jobs = jsonResponse.split("job>"); // 1, 3, 5, 7, etc will contain jobs
        for (String job : jobs) {
            String[] names = job.split("name>");
            if (names.length == 3) {
                String name = names[1];
                name = name.substring(0, name.length() - 2); // Take off </ for the closing name tag: </name>
                jobList.add(name);
//				System.out.println("name:"+name);
            }
//			System.out.println("job:"+job);
//			for(String name: names){
//				System.out.println("name:"+name);
//			}
        }
        return jobList;
    }
    /*
    public static String deleteJob(String url, String jobName) {
        Client client = Client.create();
		client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter("admin", "admin"));
        WebResource webResource = client.resource(url + "/job/" + jobName + "/doDelete");
        ClientResponse response = webResource.post(ClientResponse.class);
        String jsonResponse = response.getEntity(String.class);
        client.destroy();
//		System.out.println("Response deleteJobs:::::"+jsonResponse);
        return jsonResponse; */
/*
    public static String copyJob(String url, String newJobName, String oldJobName) {
        Client client = Client.create();
		client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter("admin", "admin"));
        WebResource webResource = client.resource(url + "/createItem?name=" + newJobName + "&mode=copy&from=" + oldJobName);
        ClientResponse response = webResource.type("application/xml").get(ClientResponse.class);
        String jsonResponse = response.getEntity(String.class);
        client.destroy();
//		System.out.println("Response copyJob:::::"+jsonResponse);
        return jsonResponse;
    } */

    //Had to uncheck "Prevent Cross Site Request Forgery exploits" to make this work.
    //To have security checked, crumb needs to somehow be included in request
    public static String createJob(String url, String newJobName, String configXML) {
        Client client = Client.create();
		client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter("admin", "admin"));
        WebResource webResource = client.resource(url + "/createItem?name=" + newJobName);
        ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, configXML);
        String jsonResponse = response.getEntity(String.class);
        client.destroy();
        System.out.println("Response createJob:::::" + jsonResponse);
        return jsonResponse;
    }
/*
    public static String readJob(String url, String jobName) {
        Client client = Client.create();
		client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter("admin", "admin"));
        WebResource webResource = client.resource(url + "/job/" + jobName + "/config.xml");
        ClientResponse response = webResource.get(ClientResponse.class);
        String jsonResponse = response.getEntity(String.class);
        client.destroy();
		System.out.println("Response readJob:::::"+jsonResponse);
        return jsonResponse;

    }*/

}