package uy.edu.ucu.aed;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_QUICKSORT = 4;
	public static final int METODO_CLASIFICACION_HEAPSORT = 5;

	public static final int METODO_CLASIFICACION_COUNTING_SORT = 6;

	/**
	 * Punto de entrada al clasificador
	 * 
	 * @param metodoClasificacion
	 * // @param orden
	 * // @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
			case METODO_CLASIFICACION_QUICKSORT:
				return ordenarPorQuickSort(datosParaClasificar);
			case METODO_CLASIFICACION_HEAPSORT:
				return ordenarPorHeapSort(datosParaClasificar);
			case METODO_CLASIFICACION_COUNTING_SORT:
				return ordenarPorCountingSort(datosParaClasificar);
			default:
				System.err.println("Este código no debería haberse ejecutado.");
				break;
		}
		return datosParaClasificar;
	}

	protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
		quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1, 1);
		return datosParaClasificar;
	}

	public int encuentraPivote(int izq, int der, int[] entrada) {
		if (izq < der) {
			int posPivote = (izq + der) / 2;
			int pivote = entrada[posPivote];
			intercambiar(entrada, posPivote, der);
			int i = izq;
			for (int j = izq; j < der; j++) {
				if (entrada[j] <= pivote) {
					intercambiar(entrada, i, j);
					i++;
				}
			}
			intercambiar(entrada, i, der);
			return i;
		}
		throw new IllegalArgumentException("El vector debe tener al menos 2 elementos");
	}

	public int encuentraPivoteRandom(int izq, int der, int[] entrada) {
		if (izq < der) {
			int posPivote = (int) (Math.random() * (der - izq + 1)) + izq;
			int pivote = entrada[posPivote];
			intercambiar(entrada, posPivote, der);
			int i = izq;
			for (int j = izq; j < der; j++) {
				if (entrada[j] <= pivote) {
					intercambiar(entrada, i, j);
					i++;
				}
			}
			intercambiar(entrada, i, der);
			return i;
		}
		throw new IllegalArgumentException("El vector debe tener al menos 2 elementos");
	}

	public int encuentraPivoteMaximo(int izq, int der, int[] entrada) {
		if (izq < der) {
			int max = izq;
			for (int i = izq + 1; i <= der; i++) {
				if (entrada[i] > entrada[max]) {
					max = i;
				}
			}

			int pivote = entrada[max];
			intercambiar(entrada, max, der);
			int i = izq;
			for (int j = izq; j < der; j++) {
				if (entrada[j] <= pivote) {
					intercambiar(entrada, i, j);
					i++;
				}
			}
			intercambiar(entrada, i, der);
			return i;
		}
		throw new IllegalArgumentException("El vector debe tener al menos 2 elementos");
	}

	private void quicksort(int[] entrada, int izq, int der, int opcionPivote) {
		if (izq < der) {
			int pivote = 0;
			switch (opcionPivote) {
				case (1):
					pivote = encuentraPivoteRandom(izq, der, entrada);
					break;
				case (2):
					pivote = encuentraPivoteMaximo(izq, der, entrada);
					break;
				default:
					pivote = encuentraPivote(izq, der, entrada);
					break;
			}

			if (izq < der) {
				quicksort(entrada, izq, pivote - 1, 1);
				quicksort(entrada, pivote + 1, der, 1);
			}
		}
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int n = datosParaClasificar.length;
		int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

		for (int inc : incrementos) {
			for (int i = inc; i < n; i++) {
				int temp = datosParaClasificar[i];
				int j = i;
				while (j >= inc && datosParaClasificar[j - inc] > temp) {
					datosParaClasificar[j] = datosParaClasificar[j - inc];
					j -= inc;
				}
				datosParaClasificar[j] = temp;
			}
		}
		return datosParaClasificar;
	}

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int j = i - 1;
				while ((j >= 0) && (datosParaClasificar[j + 1] > datosParaClasificar[j])) {
					intercambiar(datosParaClasificar, j, j + 1);
					j--;
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		int n = datosParaClasificar.length - 1;
		for (int i = 0; i <= n; i++) {
			for (int j = n; j >= (i + 1); j--) {
				if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
					intercambiar(datosParaClasificar, j - 1, j);
				}
			}
		}
		return datosParaClasificar;
	}

	protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		int n = datosParaClasificar.length;

		for (int i = (n / 2) - 1; i >= 0; i--) {
			armaHeap(datosParaClasificar, i, n - 1);
		}

		for (int i = n - 1; i > 0; i--) {
			intercambiar(datosParaClasificar, 0, i);
			armaHeap(datosParaClasificar, 0, i - 1);
		}

		return datosParaClasificar;
	}

	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		int r = primero;
		while (r * 2 + 1 <= ultimo) {
			int hijoIzq = 2 * r + 1;
			int hijoDer = hijoIzq + 1;
			int mayor = r;

			if (hijoIzq <= ultimo && datosParaClasificar[hijoIzq] > datosParaClasificar[mayor]) {
				mayor = hijoIzq;
			}
			if (hijoDer <= ultimo && datosParaClasificar[hijoDer] > datosParaClasificar[mayor]) {
				mayor = hijoDer;
			}

			if (mayor != r) {
				intercambiar(datosParaClasificar, r, mayor);
				r = mayor;
			} else {
				break;
			}
		}
	}

	protected int[] ordenarPorCountingSort(int[] datosParaClasificar) {
		if (datosParaClasificar == null || datosParaClasificar.length == 0) return datosParaClasificar;

		int N = datosParaClasificar.length;

		int u = datosParaClasificar[0];
		int v = datosParaClasificar[0];
		for (int val : datosParaClasificar) {
			if (val < u) u = val;
			if (val > v) v = val;
		}

		int rango = v - u + 1;
		int[] cuenta = new int[rango];

		for (int j = 0; j < N; j++) {
			cuenta[datosParaClasificar[j] - u]++;
		}

		for (int i = 1; i < cuenta.length; i++) {
			cuenta[i] += cuenta[i - 1];
		}

		int[] S = new int[N];
		for (int j = N - 1; j >= 0; j--) {
			int val = datosParaClasificar[j];
			int i = cuenta[val - u] - 1;
			S[i] = val;
			cuenta[val - u]--;
		}

		return S;
	}




	public static void main(String args[]) {
		TClasificador clasif = new TClasificador();

		GeneradorDatosGenericos gdgDos = new GeneradorDatosGenericos(300);
		GeneradorDatosGenericos gdgTres = new GeneradorDatosGenericos(1000);
		GeneradorDatosGenericos gdgCuatro = new GeneradorDatosGenericos(3000);
		GeneradorDatosGenericos gdgCinco = new GeneradorDatosGenericos(100);


		// 300 elementos
		int[] vectorAleatorioDos = gdgDos.generarDatosAleatorios();
		int[] vectorAscendenteDos = gdgDos.generarDatosAscendentes();
		int[] vectorDescendenteDos = gdgDos.generarDatosDescendentes();

		// 10.000 elementos
		int[] vectorAscendenteTres = gdgTres.generarDatosAscendentes();
		int[] vectorDescendenteTres = gdgTres.generarDatosDescendentes();
		int[] vectorAleatorioTres = gdgTres.generarDatosAleatorios();

		// 30.000 elementos
		int[] vectorAleatorioCuatro = gdgCuatro.generarDatosAleatorios();
		int[] vectorAscendenteCuatro = gdgCuatro.generarDatosAscendentes();
		int[] vectorDescendenteCuatro = gdgCuatro.generarDatosDescendentes();

		int[] vectorDatosAleatoriosCinco = gdgCinco.generarDatosAleatorios();
		int[] vectorDescendenteCinco = gdgCinco.generarDatosDescendentes();
		int[] vectorAscendenteCinco = gdgCinco.generarDatosAscendentes();


		// TA4 Ejercicio 1 Parte 1 Punto 4
		// Se puede cambiar la función pivote en el método ordenarPorQuicksort
		System.out.println();
		System.out.println("QUICKSORT DESCENDENTE CON 300: ");
		int[] resDescendenteDos = clasif.clasificar(vectorDescendenteDos, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resDescendenteDos.length; i++) {
			System.out.print(resDescendenteDos[i] + " ");
		}

		System.out.println();
		System.out.println("QUICKSORT ASCENDENTE CON 300: ");
		int[] resAscendenteDos = clasif.clasificar(vectorAscendenteDos, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAscendenteDos.length; i++) {
			System.out.print(resAscendenteDos[i] + " ");
		}
		System.out.println();
		System.out.println("QUICKSORT ALEATORIO CON 300: ");
		int[] resAleatorioDos = clasif.clasificar(vectorAleatorioDos, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAleatorioDos.length; i++) {
			System.out.print(resAleatorioDos[i] + " ");
		}

		System.out.println();
		System.out.println("QUICKSORT DESCENDENTE CON 10000: ");
		int[] resDescendenteTres = clasif.clasificar(vectorDescendenteTres, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resDescendenteTres.length; i++) {
			System.out.print(resDescendenteTres[i] + " ");
		}

		System.out.println();
		System.out.println("QUICKSORT ASCENDENTE CON 10000: ");
		int[] resAscendenteTres = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAscendenteTres.length; i++) {
			System.out.print(resAscendenteTres[i] + " ");
		}
		System.out.println();
		System.out.println("QUICKSORT ALEATORIO CON 10000: ");
		int[] resAleatorioTres = clasif.clasificar(vectorAleatorioTres, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAleatorioDos.length; i++) {
			System.out.print(resAleatorioTres[i] + " ");
		}

		// TA4 Ejercicio 2
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 300 ASCENDENTE:");
		int[] resInsercionAscendente300 = clasif.clasificar(vectorAscendenteDos, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAscendente300.length; i++) {
			System.out.print(resInsercionAscendente300[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 300 DESCENDENTE:");
		int[] resInsercionDescendente300 = clasif.clasificar(vectorDescendenteDos, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionDescendente300.length; i++) {
			System.out.print(resInsercionDescendente300[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 300 ALEATORIO:");
		int[] resInsercionAleatorio300 = clasif.clasificar(vectorAleatorioDos, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAleatorio300.length; i++) {
			System.out.print(resInsercionAleatorio300[i] + " ");
		}

		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 1000 ASCENDENTE:");
		int[] resInsercionAscendente1000 = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAscendente1000.length; i++) {
			System.out.print(resInsercionAscendente1000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 1000 DESCENDENTE:");
		int[] resInsercionDescendente1000 = clasif.clasificar(vectorDescendenteTres, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionDescendente1000.length; i++) {
			System.out.print(resInsercionDescendente1000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 1000 ALEATORIO:");
		int[] resInsercionAleatorio1000 = clasif.clasificar(vectorAleatorioTres, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAleatorio1000.length; i++) {
			System.out.print(resInsercionAleatorio1000[i] + " ");
		}

		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 3000 ASCENDENTE:");
		int[] resInsercionAscendente3000 = clasif.clasificar(vectorAscendenteCuatro, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAscendente3000.length; i++) {
			System.out.print(resInsercionAscendente3000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 3000 DESCENDENTE:");
		int[] resInsercionDescendente3000 = clasif.clasificar(vectorDescendenteCuatro, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionDescendente3000.length; i++) {
			System.out.print(resInsercionDescendente3000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 3000 ALEATORIO:");
		int[] resInsercionAleatorio3000 = clasif.clasificar(vectorAleatorioCuatro, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAleatorio3000.length; i++) {
			System.out.print(resInsercionAleatorio3000[i] + " ");
		}

		System.out.println();
		System.out.println("SHELL ALEATORIO:");
		int[] resAleatorio = clasif.clasificar(vectorAleatorioCuatro, METODO_CLASIFICACION_SHELL);
		for (int i = 0; i < resAleatorio.length; i++) {
			System.out.print(resAleatorio[i] + " ");
		}
		System.out.println();
		System.out.println("SHELL ASCENDENTE:");
		int[] resAscendente = clasif.clasificar(vectorAscendenteCuatro, METODO_CLASIFICACION_SHELL);
		for (int i = 0; i < resAscendente.length; i++) {
			System.out.print(resAscendente[i] + " ");
		}
		System.out.println();
		System.out.println("SHELL DESCENDENTE:");
		int[] resDescendente =clasif.clasificar(vectorDescendenteCuatro, METODO_CLASIFICACION_SHELL);
		for (int i = 0; i < resDescendente.length; i++) {
			System.out.print(resDescendente[i] + " ");
		}

		System.out.println("BURBUJA ALEATORIO:");
		int[] bubbleAleatorio = clasif.clasificar(vectorAleatorioCuatro, METODO_CLASIFICACION_BURBUJA);
		for (int i = 0; i < bubbleAleatorio.length; i++) {
			System.out.print(bubbleAleatorio[i] + " ");
		}
		System.out.println();
		System.out.println("BURBUJA ASCENDENTE:");
		int[] bubbleAscendente = clasif.clasificar(vectorAscendenteCuatro, METODO_CLASIFICACION_BURBUJA);
		for (int i = 0; i < bubbleAscendente.length; i++) {
			System.out.print(bubbleAscendente[i] + " ");
		}
		System.out.println();
		System.out.println("BURBUJA DESCENDENTE:");
		int[] bubbleDescendente =clasif.clasificar(vectorDescendenteCuatro, METODO_CLASIFICACION_BURBUJA);
		for (int i = 0; i < bubbleDescendente.length; i++) {
			System.out.print(bubbleDescendente[i] + " ");
		}

		System.out.println();
		System.out.println("HEAPSORT ALEATORIO CON 300:");
		int [] resHeapsortAleatorio = clasif.clasificar(vectorAleatorioTres, METODO_CLASIFICACION_HEAPSORT);
		for (int i = 0; i < resHeapsortAleatorio.length; i++) {
			System.out.print(resHeapsortAleatorio[i] + " ");
		}

		System.out.println();
		System.out.println("HEAPSORT ASCENDENTE CON 1000:");
		int[] resHeapsortAscendente = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_HEAPSORT);
		for (int i = 0; i < resHeapsortAscendente.length; i++) {
			System.out.print(resHeapsortAscendente[i] + " ");
		}

		System.out.println();
		System.out.println("HEAPSORT DESCENDENTE CON 3000:");
		int[] resHeapsortDescendente = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_HEAPSORT);
		for (int i = 0; i < resHeapsortDescendente.length; i++) {
			System.out.print(resHeapsortDescendente[i] + " ");
		}

		System.out.println();
		System.out.println("COUNTING SORT ALEATORIO CON 100:");
		int[] resCountingAleatorio = clasif.clasificar(vectorDatosAleatoriosCinco,METODO_CLASIFICACION_COUNTING_SORT);
		for (int i = 0; i < resCountingAleatorio.length; i++) {
			System.out.print(resCountingAleatorio[i] + " ");
		}

		System.out.println();
		System.out.println("COUNTING SORT DESCENDENTE CON 100:");
		int[] resCountingDescendiente = clasif.clasificar(vectorDescendenteCinco,METODO_CLASIFICACION_COUNTING_SORT);
		for (int i = 0; i < resCountingDescendiente.length; i++) {
			System.out.print(resCountingDescendiente[i] + " ");
		}

		System.out.println();
		System.out.println("COUNTING SORT ASCENDENTE CON 100:");
		int[] resCountingAscendente = clasif.clasificar(vectorAscendenteCinco,METODO_CLASIFICACION_COUNTING_SORT);
		for (int i = 0; i < resCountingAscendente.length; i++) {
			System.out.print(resCountingAscendente[i] + " ");
		}
	}
}