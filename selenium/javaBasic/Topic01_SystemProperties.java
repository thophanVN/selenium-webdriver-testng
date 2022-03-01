package javaBasic;

public class Topic01_SystemProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String projectPath=System.getProperty("user.dir");
		System.out.print(projectPath);
	}

}
