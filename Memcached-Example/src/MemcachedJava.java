import net.spy.memcached.*;
import java.net.*;
import java.util.concurrent.Future;
public class MemcachedJava {

	public static void main(String[] args) {
		try {
			MemcachedClient client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11111));
			
			System.out.println("Successful");
			System.out.println(client.get("tut"));
			
			Future fo = client.set("tutorialspoint", 900, "Free Education");
			System.out.println("Status: "+fo.get());
			System.out.println(client.get("tutorialspoint"));
			
			//try to add with existing key
			fo = client.add("tutorialspoint", 20, "Example of add");
			System.out.println("Add status :"+fo.get());
			fo = client.add("Add", 20, "Example of add");
			System.out.println("Add status :"+fo.get());
			System.out.println(client.get("Add"));
			
			//Replace value of existing key
			fo = client.replace("Add",  900,"Replaved value");
			System.out.println("Replace Status: "+fo.get());
			System.out.println(client.get("Add"));
			
			fo = client.append("Add", " Appended value");
			System.out.println(client.get("Add"));
			fo =  client.add("tp", 900, "Hello how are you");
			CASValue value = client.gets("tp");
			System.out.println("Token value :"+ value);
			CASResponse response = client.cas("tp", value.getCas(), "CAS VALUE Changed");
			System.out.println("CAS response :"+ response);
			System.out.println(client.get("tp"));
			
		client.shutdown();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
}
