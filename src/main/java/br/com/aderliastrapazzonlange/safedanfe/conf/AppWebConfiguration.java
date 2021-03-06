package br.com.aderliastrapazzonlange.safedanfe.conf;

public class AppWebConfiguration {
	
	private static final String fileRootPath = System.getProperty("user.dir") + "/src/main/resources/XML/";
	private static final String filePathXML = "origem/";
	private static final String filePathOther = "xmlOther/";
	private static final String filePathBKP = "xmlBKP/";
	private static final String filePathError = "error/";
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
	public static String getFilepatherror() {
		return filePathError;
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
	 

	
}
