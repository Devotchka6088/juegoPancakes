package juegoPancakes;

import java.util.LinkedList;

public class Main {
	
	static String ordenPrin = "";

	public static void main(String[] args) {
		
		char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String ordenRan = "";
		int nDiscos = 4;
		
		for(int i=0;i<=nDiscos-1;i++) {
			ordenPrin+=letras[i];
		}
		System.out.println("Inicio     > "+ordenPrin);
		
		//ordenRan = shuffle();
		ordenRan = "CDAB";
		
		algoritmoAleatorio(ordenRan);
		
		busquedaAmplitud(ordenRan);

	}
	
	private static String shuffle() {

		char[] pos = ordenPrin.toCharArray();
		//creando cadena vacia
		String orden = "";
		//ciclo para poner aleatorios
		for(int i=0;i<ordenPrin.length();i++) {
			//numero aleatorio entre valores de ndiscos
			int ran = (int)(Math.random()*ordenPrin.length());
			//verifica que el disco este disponible y no duplicado
			if(pos[ran]!=0) {
				//concatena el numero del disco al string
				orden+=pos[ran];
				//marca el disco como no disponible
				pos[ran]=0;
			}else {
				//en caso de no estar disponible se reintenta regresando el ciclo
				i--;
			}
		}
		System.out.println("Aleatorios > "+orden);
		return orden;
	}
	
	private static void algoritmoAleatorio(String ordenRan) {
		
		int ran,ciclos;
		char[] pos = ordenRan.toCharArray();
		String orden = "";
		//ciclo para dar solucion aleatoria al juego
		//la condicion verifica que las cadenas no son identicas y repite
		for(ciclos=0;!veriSol(orden);ciclos++) {
			//despues de verificar se reinicia la cadena
			orden = "";
			//se genera numeros aleatorios dentro del rango de posiciones del arreglo
			ran = (int)(Math.random()*ordenRan.length());
			//invierte las posiciones de la posicion 0 a la aleatoria
			for(int i=ran;i>=0;i--) {
				orden+=pos[i];
			}
			//completa la cadena con los valores de disco faltantes despues de la pos aleatoria
			for(int i=ran+1;i<ordenRan.length();i++) {
				orden+=pos[i];
			}
			//se reemplazan las nuevas posiciones
			pos = orden.toCharArray();
		}
		System.out.println("\n"+"Ciclos     > "+(ciclos+1));
	}
	
	private static void busquedaAmplitud(String ordenRan) {
		
		char[] pos = ordenRan.toCharArray();
		String orden = "";
		LinkedList<String> colaDiscos= new LinkedList<String>();
		int[] noRegreso = new int[1000];
		int contador = 0;
		Integer[] padre = new Integer[1000];
		
		colaDiscos.add(ordenRan);
		noRegreso[0] = 0;
		padre[0] = null;
		
		for(int a=0;!veriSol(orden);a++) {
			
			pos = colaDiscos.get(a).toCharArray();
			
			for(int i=1;i<pos.length;i++) {
				
				if(noRegreso[a]!=i) {
					
					contador++;
					padre[contador] = a;
					noRegreso[contador] = i;
					
					for(int j=i;j>=0;j--) {
						orden+=pos[j];
					}
					
					for(int j=i+1;j<pos.length;j++) {
						orden+=pos[j];
					}
					
					if(veriSol(orden)) {
						break;
					}
					
					colaDiscos.add(orden);
					orden = "";
					
				}
			}
		}
		System.out.print("\n");
		for(int z=1;z>0;z++) {
			
			if(noRegreso[contador]==0) {
				System.out.println("Orden      > "+orden);
			}else {
				System.out.println("Orden      > "+orden+" > "+(noRegreso[contador]+1));
			}
			
			pos = orden.toCharArray();
			int x = noRegreso[contador];

			if(!(orden.contentEquals(ordenRan))) {
				orden = "";
				
				for(int j=x;j>=0;j--) {
					orden+=pos[j];
				}
				
				for(int j=x+1;j<pos.length;j++) {
					orden+=pos[j];
				}
				
				contador = padre[contador];
			} else {
				System.out.println("Distancia > "+z);
				break;
			}	
		}
	}
	
	
	private static boolean veriSol(String ordenRan) {
		
		return ordenPrin.equals(ordenRan);
		
	}
}