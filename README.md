# common
E:\util\01.개발\tomcat\apache-tomcat-8.5.41-windows-x64\apache-tomcat-8.5.41\lib


%CATALINA_HOME%\conf\catalina.properties 파일을 열어서

아래 라인을 찾는다.

common.loader="${catalina.base}/lib","${catalina.base}/lib/*.jar","${catalina.home}/lib","${catalina.home}/lib/*.jar","${catalina.home}/usrlib/*.jar"

,"${catalina.home}/usrlib/*.jar" 추가했다.