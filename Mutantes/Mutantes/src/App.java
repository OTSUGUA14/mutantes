import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ingres el tamaño de la tabla");
        int tamaño=scanner.nextInt();
        String [] mutante=new String[tamaño];
        String[] letras = {"A", "T", "C", "G"};

        // Crear un objeto Random para generar números aleatorios
        Random random = new Random();
        for (int i = 0; i < tamaño; i++) {
            StringBuilder fila = new StringBuilder(); // Usamos StringBuilder para construir la cadena
            for (int j = 0; j < tamaño; j++) {
                int indice = random.nextInt(letras.length);
                fila.append(letras[indice]); // Añadir la letra a la fila
            }
            mutante[i] = fila.toString(); // Asignar la fila completa al arreglo
            System.out.println(mutante[i]+""); // Mostrar la fila generada
        }
       

        checkMutant(mutante);

        // if (MutantDetector.isMutant(mutante)) {
        //     System.out.println("Es un mutante.");
        // } else {
        //     System.out.println("No es un mutante.");
        // }
    }
    public static void checkMutant(String[] mutante) {
        boolean ismutan=false;
        for (int i = 0; i < mutante.length; i++) {
            for (int j = 0; j < mutante[i].length(); j++) {
            
                
                // Comprobar fila
                if (j + 3 < mutante[i].length() && checkSequence(mutante[i], j, 1)) {
                    ismutan=true;
                    System.out.println("Es mutante en la fila " + (i + 1));
                }

                // Comprobar columna
                if (i + 3 < mutante.length && checkSequenceVertical(mutante, j, i)) {
                    ismutan=true;
                    System.out.println("Es mutante en la columna " + (j + 1));
                }

                // Comprobar diagonal principal
                if (i + 3 < mutante.length && j + 3 < mutante[i].length() && checkSequenceDiagonal(mutante, i, j, 1)) {
                    ismutan=true;
                    System.out.println("Es mutante en la diagonal principal desde fila " + (i + 1) + ", columna " + (j + 1));
                }

                // Comprobar diagonal secundaria
                if (i + 3 < mutante.length && j - 3 >= 0 && checkSequenceDiagonal(mutante, i, j, -1)) {
                    ismutan=true;
                    System.out.println("Es mutante en la diagonal secundaria desde fila " + (i + 1) + ", columna " + (j + 1));
                }
            }
        }
        if (!ismutan){
            System.out.println("no es mutante");
        }
    }
   
    private static boolean checkSequence(String str, int start, int step) {
        char firstChar = str.charAt(start);
        System.out.println(firstChar+" ");
        System.out.println(str);
        for (int i = 1; i < 4; i++) {
            if (str.charAt(start + i * step) != firstChar) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkSequenceVertical(String[] mutante, int col, int startRow) {
        char firstChar = mutante[startRow].charAt(col);
        for (int i = 1; i < 4; i++) {
            if (mutante[startRow + i].charAt(col) != firstChar) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkSequenceDiagonal(String[] mutante, int startRow, int startCol, int direction) {
        char firstChar = mutante[startRow].charAt(startCol);
        for (int i = 1; i < 4; i++) {
            int newRow = startRow + i;
            int newCol = startCol + direction * i; // +1 para diagonal principal, -1 para diagonal secundaria
            if (mutante[newRow].charAt(newCol) != firstChar) {
                return false;
            }
        }
        return true;
    }
}


// public class MutantDetector {
//     private static final int MIN_SEQUENCE_LENGTH = 4;
//     private static final char[] BASES = {'A', 'T', 'C', 'G'};

//     public static boolean isMutant(String[] dna) {
//         int n = dna.length;
//         int count = 0;

//         // Check rows
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
//                 if (checkSequence(dna[i].substring(j, j + MIN_SEQUENCE_LENGTH))) {
//                     count++;
//                 }
//             }
//         }

//         // Check columns
//         for (int i = 0; i <= n - MIN_SEQUENCE_LENGTH; i++) {
//             for (int j = 0; j < n; j++) {
//                 StringBuilder sb = new StringBuilder();
//                 for (int k = 0; k < MIN_SEQUENCE_LENGTH; k++) {
//                     sb.append(dna[i + k].charAt(j));
//                 }
//                 if (checkSequence(sb.toString())) {
//                     count++;
//                 }
//             }
//         }

//         // Check diagonals
//         for (int i = 0; i <= n - MIN_SEQUENCE_LENGTH; i++) {
//             for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
//                 StringBuilder sb1 = new StringBuilder();
//                 StringBuilder sb2 = new StringBuilder();
//                 for (int k = 0; k < MIN_SEQUENCE_LENGTH; k++) {
//                     sb1.append(dna[i + k].charAt(j + k));
//                     sb2.append(dna[i + k].charAt(j + MIN_SEQUENCE_LENGTH - 1 - k));
//                 }
//                 if (checkSequence(sb1.toString()) || checkSequence(sb2.toString())) {
//                     count++;
//                 }
//             }
//         }

//         return count > 1;
//     }

//     private static boolean checkSequence(String sequence) {
//         for (char base : BASES) {
//             if (sequence.equals(new String(new char[MIN_SEQUENCE_LENGTH]).replace('\0', base))) {
//                 return true;
//             }
//         }
//         return false;
//     }
// }