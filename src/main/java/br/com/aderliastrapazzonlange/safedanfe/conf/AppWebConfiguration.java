package br.com.aderliastrapazzonlange.safedanfe.conf;

public class AppWebConfiguration {
	
	private static final String fileRootPath = "/home/aderlian/Downloads/XML/origem";
	private static final String filePathXML = "/home/aderlian/Downloads/XML/origem";
	private static final String filePathOther = "/home/aderlian/Downloads/XML/xmlOther/";
	private static final String filePathBKP = "/home/aderlian/Downloads/XML/xmlBKP/";
	private static final String filePathError = "error/";
	private static final String syncTime = "60";
	private static final String jpaContextual = "true";
	private static final String jpaDriverClass = "com.mysql.cj.jdbc.Driver";
	private static final String jpaURL = "jdbc:mysql://localhost:3306/safedanfe";
	private static final String jpaUserName = "admin";
	private static final String jpaPassword = "toor";
	private static final String jpaDDLAuto = "update";
	
	public static String getFilerootpath() {
		return fileRootPath;
	}
	public static String getFilepathxml() {
		return filePathXML;
	}
	public static String getFilepathother() {
		return filePathOther;
	}
	public static String getFilepathbkp() {
		return filePathBKP;
	}
	public static String getSynctime() {
		return syncTime;
	}
	public static String getJpacontextual() {
		return jpaContextual;
	}
	public static String getJpadriverclass() {
		return jpaDriverClass;
	}
	public static String getJpaurl() {
		return jpaURL;
	}
	public static String getJpausername() {
		return jpaUserName;
	}
	public static String getJpapassword() {
		return jpaPassword;
	}
	public static String getJpaddlauto() {
		return jpaDDLAuto;
	}
	public static String getFilepatherror() {
		return filePathError;
	}
	
	
	
	
	 
//	spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
//
//	#Banco Local
//	spring.datasource.url=jdbc:mysql://localhost:3306/safedanfe
//	spring.datasource.username=admin
//	spring.datasource.password=toor
//	spring.jpa.hibernate.ddl-auto=update

	
}
