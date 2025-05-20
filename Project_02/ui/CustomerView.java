package ui;
import bean.Customer;
import service.CustomerList;
import util.CMUtility;

/**
 * 
 * @author ww
 * @version 
 * @since May 19, 2025
 * @description CustomerView is the main module, responsible for making the menu 
 * explicit and handling user actions.
 * 
 */


public class CustomerView {

	private CustomerList customerList = new CustomerList(10);
	
	public CustomerView() {
		
		Customer customer1 = new Customer("张三",'男',28,"18861478900","zhangsan@gmail.com");
		customerList.addCustomer(customer1);
		Customer customer2 = new Customer("李四",'男',27,"12345678901","lisi@gmail.com");
		customerList.addCustomer(customer2);
		
	}
	
	//显示《客户信息管理软件》界面的方法
	public void enterMainMenu() {
		
		boolean loopFlag = true;
		
		while(loopFlag) {
			System.out.println("\n------客户信息管理软件------");
			System.out.println("1 添加客户");
			System.out.println("2 修改客户");
			System.out.println("3 删除客户");
			System.out.println("4 客户列表");
			System.out.println("5 退出\n");
			System.out.println("请选择1-5：");
			
			char menu = CMUtility.readMenuSelection();
			switch(menu) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listCustomer();
				break;
			case '5':
				System.out.println("确认是否退出(Y/N)：");
				
				char isExit = CMUtility.readConfirmSelection();
				if(isExit == 'Y') {
					loopFlag = false;
				}
			}
		}
	}
	
	
	//添加客户
	public void addNewCustomer() {
		System.out.println("------添加客户------");
		System.out.println("姓名");
		String name = CMUtility.readString(10);
		System.out.println("性别:");
		char gender = CMUtility.readChar();
		System.out.println("年龄:");
		int age = CMUtility.readInt();
		System.out.println("电话:");
		String phone = CMUtility.readString(13);
		System.out.println("邮箱:");
		String email = CMUtility.readString(30);
		
		//将上述数据封装到对象中
		Customer customer = new Customer(name,gender,age,phone,email);
		
		boolean isSuccess = customerList.addCustomer(customer);
		if(isSuccess) {
			System.out.println("------添加成功------");
		}else {
			System.out.println("------客户目录已满，添加失败------");
		}
		
	}
	
	//修改客户
	public void modifyCustomer() {
		System.out.println("------修改客户------");
		
		Customer cust;
		int number;

		for(; ; ) {
			System.out.println("请选择修改客户编号（-1退出）");
			number = CMUtility.readInt();
			if(number == -1) { //回到主页面
				return;
			}
			
			cust = customerList.getCustomer(number - 1);
			if(cust == null) {
				System.out.println("无法找到指定客户）");
			}else { //找到了相应编号的客户
				break;
			}
			
		}  //修改客户信息
		System.out.println("姓名：" + cust.getName());
		String name = CMUtility.readString(10,cust.getName());
		System.out.println("性别：" + cust.getGender());
		char gender = CMUtility.readChar(cust.getGender());
		System.out.println("年龄：" + cust.getAge());
		int age = CMUtility.readInt(cust.getAge());
		System.out.println("电话：" + cust.getPhone());
		String phone = CMUtility.readString(13, cust.getPhone());
		System.out.println("邮箱：" + cust.getEmail());
		String email = CMUtility.readString(30, cust.getEmail());
		
		Customer newCust = new Customer(name, gender, age, phone, email);
		
		boolean isReplaced = customerList.replaceCustomer(number - 1, newCust);
		if(isReplaced) {
			System.out.println("------修改完成------");
		}else {
			System.out.println("------修改失败------");
		}
	}
	
	//删除客户
	public void deleteCustomer() {
		System.out.println("------删除客户------");
		int number;
		for(; ; ) {
			System.out.println("请选择修改客户编号（-1退出）");
			number = CMUtility.readInt();
			if(number == -1) {
				return;
			}
			Customer cust = customerList.getCustomer(number - 1);
			if(cust == null) {
				System.out.println("无法找到指定客户）");
			}else { //找到了相应编号的客户
				break;
			}
		}
		//找到了指定客户
		System.out.print("是否确认删除 Y/N");
		char isDelete = CMUtility.readConfirmSelection();
		if(isDelete == 'Y') {
			
			boolean deleteSuccess = customerList.deleteCustomer(number - 1);
			if(deleteSuccess) {
				System.out.print("删除成功");
			}else {
				System.out.print("删除失败");
			}
			
		}else {
			return;
		}
	}
	
	//显示客户列表
	public void listCustomer(){
		System.out.println("------客户列表------");
		
		int total = customerList.getTotal();
		
		if (total == 0) {
			System.out.print("没有客户记录");
			
		}else {
			System.out.print("编号\t姓名\t性别\t年龄\t电话\t\t邮箱\n"); 
			Customer[] custs = customerList.getAllCustomers();
			for(int i = 0; i < custs.length; i++) {
				Customer cust = custs[i];
				
				System.out.print((i+1)+ "\t" + cust.getName() + "\t" + cust.getGender() + "\t" 
				+ cust.getAge() + "\t" + cust.getPhone() + "\t" + cust.getEmail() + "\n");
			}
		}
		System.out.println("\n------客户列表完成------");
	}
	
	//程序入口
	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.enterMainMenu();
	}
}
