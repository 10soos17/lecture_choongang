package exercise;

public class ShopServiceExample {

	public static void main(String[] args) {
		//ShopService obj = new ShopService();//old: 객체 생성

		ShopService obj1 = ShopService.getIncetance(); 
		ShopService obj2 = ShopService.getIncetance();
		
		System.out.println(obj1);
		System.out.println(obj2);

		if(obj1 == obj2) {
			System.out.println("같은 ShopService 객체 입니다.");
		}else {
			System.out.println("다른 ShopService 객체 입니다.");
		}
		
	}
	

	
	
}
