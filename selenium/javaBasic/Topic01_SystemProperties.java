package javaBasic;

public class Topic01_SystemProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//get path locate the coding file
		String projectPath=System.getProperty("user.dir");
		System.out.println(projectPath);
		
		//get the OSname
		String osName = System.getProperty("os.name");
		System.out.println(osName);
	}

}
