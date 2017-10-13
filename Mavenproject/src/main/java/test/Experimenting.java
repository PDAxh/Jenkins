package test;

import java.io.FileOutputStream;
import java.io.IOException;

public class Experimenting {

    public static void main(String arg[]) throws IOException {

        String gitRepo = "https://github.com/MattiasC86/MavenProject2";

        //createConfigFile(gitRepo);
        runCurl();
    }


    public static void createConfigFile(String gitRep) throws IOException {
        String data = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<project>\n" +
                "  <description></description>\n" +
                "  <keepDependencies>false</keepDependencies>\n" +
                "  <properties/>\n" +
                "  <scm class=\"hudson.plugins.git.GitSCM\" plugin=\"git@3.6.0\">\n" +
                "    <configVersion>2</configVersion>\n" +
                "    <userRemoteConfigs>\n" +
                "      <hudson.plugins.git.UserRemoteConfig>\n" +
                "        <url>" + gitRep + "</url>\n" +
                "      </hudson.plugins.git.UserRemoteConfig>\n" +
                "    </userRemoteConfigs>\n" +
                "    <branches>\n" +
                "      <hudson.plugins.git.BranchSpec>\n" +
                "        <name>*/master</name>\n" +
                "      </hudson.plugins.git.BranchSpec>\n" +
                "    </branches>\n" +
                "    <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>\n" +
                "    <submoduleCfg class=\"list\"/>\n" +
                "    <extensions/>\n" +
                "  </scm>\n" +
                "  <canRoam>true</canRoam>\n" +
                "  <disabled>false</disabled>\n" +
                "  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>\n" +
                "  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>\n" +
                "  <triggers/>\n" +
                "  <concurrentBuild>false</concurrentBuild>\n" +
                "  <builders/>\n" +
                "  <publishers/>\n" +
                "  <buildWrappers/>\n" +
                "</project>";
        FileOutputStream out = new FileOutputStream("c://users//macru1//testFile2.xml");
        out.write(data.getBytes());
        out.close();
    }

    public static void runCurl() throws IOException {
        Runtime.getRuntime().exec("curl -s -XPOST 'http://admin:admin@localhost:8080/createItem?name=CopiedJob7' --data-binary @mylocaltestconfig.xml -H \"Content-Type:text/xml\"");
    }

}
