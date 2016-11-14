package us.codecraft.jobhunter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class LambadaExample {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("11111111");
				
			}
		});
		thread.start();
		
		Thread thread1 = new Thread(() -> {
			System.out.println("2222222");
		});
		thread1.start();
		
		Collections.sort(new ArrayList<>(),new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return 0;
			}
		});
		
		
		Collections.sort(new ArrayList<>(),(o1,o2) -> {
				return 0;
			}
		);
		
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		/*for (String feature : features) {
			System.out.println(feature);
		}*/
		
		features.forEach((n) -> System.out.println(n));
		features.forEach(System.out::println);
		
	}

}
