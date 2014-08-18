package numeros;

import java.util.Scanner;

public class Pares {

	public static void main (String args[]){
		Scanner scn = new Scanner(System.in);
		
		System.out.println("ingrese un número. ");
		int ingreso = scn.nextInt();
		
		if((ingreso % 2)==0){
			System.out.println(ingreso + " es un número par!");
		}else{
			System.out.println(ingreso + " es un número impar!");
		}
		
	}
	
}
