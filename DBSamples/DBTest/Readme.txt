1) Specify values as per your environment in db.properties

2) Include Ojbc6.jar or ojdbc7.jar in classpath.
   In weblogic 12c 1.1.2, you can find this jar @ C:\Oracle\Middleware\Oracle_Home\oracle_common\modules\oracle.jdbc_11.2.0
3) To lookup connection from weblogic , include wlfullclient.jar.
   Refer to steps@ http://docs.oracle.com/cd/E24329_01/web.1211/e24378/jarbuilder.htm#BABCGHFH 
   to generate this jar  (just go to weblogic , server/lib and execute java -jar wljarbuilder.jar)  
   
4) To create datasource in weblogic
	login to admin server console --> click on datasources --> new --> generic datasource --> for jndi , enter some value like jndi/oracleDB , select databasetype=oracle
	--> next --> next -- > enter database name , host name , port , username and password.--> next --> test the connection -->
	next --> select admin server has target --> finish