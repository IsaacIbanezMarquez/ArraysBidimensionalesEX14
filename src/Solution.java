







public class Solution
{

    /*
5) El youtuber Folagor03 contacta con nosotros para hacer un programa que nos muestre la tabla de tipos de Pokemon (tiene un problema serio con ello).
En la solución os dejo un fichero java con los datos necesarios: la tabla de tipos y los tipos de Pokemon. Es el fichero llamado Constantes,java, copialo a tu Proyecto, si lo quieres hacer de cero.

Los datos que contiene la tabla de tipos es la siguiente:



5: el atacante hace la mitad de daño al oponente (No es muy eficaz)
1: el atacante hace un daño neutro al oponente (No se da información) (en la imagen representa al -)
2: el atacante hace el doble de daño al oponente (Es muy eficaz)
0: el atacante hace un daño nulo al oponente (No afecta)
Las filas de la matriz es el Pokemon que ataca.

Las columnas son el pokemon que recibe el ataque.

Por ejemplo, Fila 4 (Eléctrico) ataca a un pokemon de la Columna 1 (Agua), habrá un 2 porque eléctrico es eficaz contra el agua.

Para mayor comodidad del usuario, al poner los tipos de pokemon, se los mostraremos de esta manera:

ACERO
AGUA
BICHO
….

Así hasta el final. Mirar el vector con los tipos.

Las opciones que le daremos a Folagor03 son:

Mostrar debilidades: pedimos un tipo o dos (tenemos que preguntárselo al usuario) y nos mostrara todos los tipos que hacen un daño eficaz (recordar un 2) a este tipo.
Por ejemplo, si Folagor03 elige DEBILIDADES de FUEGO, le deberían aparecer estos tipos:

AGUA
ROCA
TIERRA
Si elige dos tipos de pokemon, por ejemplo FUEGO y TIERRA, habrá que ver esos dos tipos y multiplicar las debilidades y mostrar el multiplicador de daño.

AGUA : x4 (2 * 2 = 4, doblemente eficaz)
TIERRA : x2 (2 * 1 = 2, la tierra es neutro con la tierra)
Fíjate que no aparece el tipo ROCA, ya que TIERRA tiene resistencia y hace que un ataque sea neutro (0.5 * 2 = 1)

Mostrar todas los tipos eficaces: pedimos un solo tipo (un ataque al final solo es de un solo tipo) y mostramos que tipos son débiles contra ellos.
Por ejemplo, si elegimos ACERO, los pokemon a los que hace un “Es muy eficaz” son:

HADA
HIELO
ROCA


Mostrar toda la información relativa de un tipo: pedimos un tipo y nos mostrara el daño de cada uno. Según el valor poner los siguientes valores:
1 : NEUTRO
0 : NO AFECTA
2 : MUY EFICAZ
5 : NO MUY EFICAZ
Por ejemplo si elegimos AGUA, mostraría algo así:

ACERO : NEUTRO
AGUA: NO MUY EFICAZ
BICHO: NEUTRO
Así con todos los tipos

Mostrar eficacia de un tipo a otro: pedimos dos tipos de pokemon, el primero será el atacante y el segundo será el oponente. Simplemente diremos si es eficaz o no, neutro o no le afecta el ataque.
Por ejemplo, si elijo el tipo TIERRA de atacante y VOLADOR de oponente este me deberá mostrar:

NO AFECTA
También tiene que pedir si quiere que el tipo del oponente sea de uno o dos tipos.

 */

    /*Tipos actuales de pokemon*/
    public static String[] tiposPokemon = {
            "ACERO",
            "AGUA",
            "BICHO",
            "DRAGÓN",
            "ELÉCTRICO",
            "FANTASMA",
            "FUEGO",
            "HADA",
            "HIELO",
            "LUCHA",
            "NORMAL",
            "PLANTA",
            "PSÍQUICO",
            "ROCA",
            "SINIESTRO",
            "TIERRA",
            "VENENO",
            "VOLADOR"
    };

    /*Mensaje segun la eficacia*/
    public static String[] MENSAJES_EFICACIAS = {
            "NO ES MUY EFICAZ",
            "NEUTRO",
            "ES MUY EFICAZ",
            "NO AFECTA",
            "DOBLEMENTE EFICAZ",
            "DOBLEMENTE NO MUY EFICAZ"
    };

    /*INDICES de los mensaje anteriores
        Asi no debes recordar en que posicion esta cada uno
    */

    public static int INDICE_NME=0;
    public static int INDICE_NEUTRO=1;
    public static int INDICE_EME=2;
    public static int INDICE_NA=3;
    public static int INDICE_DE=4;
    public static int INDICE_DNE=5;

    /*Eficiacias*/

    public static double EFICACIA_DE=4; /*DOBLEMENTE EFICAZ*/
    public static double EFICACIA_EME=2; /*ES MUY EFICAZ*/
    public static double EFICACIA_NEUTRO=1;
    public static double EFICACIA_NME=0.5; /*NO ES MUY EFICAZ*/
    public static double EFICACIA_DNE=0.25; /*DOBLEMENTE NO ES EFICAZ*/
    public static double EFICACIA_NA=0;  /*NO AFECTA*/

    /*
        FILAS = Pokemon atacante
        COLUMNAS = Pokemon que recibe el ataque

        Significados de los números:
            - 0.5: el atacante hace la mitad de daño al oponente (No es muy eficaz)
            - 1: el atacante hace un daño neutro al oponente (No se da información pero diremos neutro)
            - 2: el atacante hace el doble de daño al oponente (Es muy eficaz)
            - 0: el atacante hace un daño nulo al oponente (No afecta)

    */
    public static double[][] efectividadesPokemon = {

            /*ACERO   AGUA   BICHO   DRAGON   ELÉC   FANT   FUEGO   HADA   HIELO   LUCHA   NORMAL   PLANTA   PSI   ROCA   SINIE  TIERRA   VENENO   VOLADOR   */

            { 0.5   , 0.5  ,   1   ,   1    , 0.5  ,  1   ,  0.5   ,  2  ,   2   ,   1   ,    1    ,   1   ,  1   ,  2  ,   1   ,   1   ,    1   ,   1},   //ACERO
            {  1    , 0.5  ,   1   ,  0.5   ,  1   ,  1   ,   2    ,  1  ,   1   ,   1   ,    1    ,  0.5  ,  1   ,  2  ,   1   ,   2   ,    1   ,   1},   //AGUA
            { 0.5   ,  1   ,   1   ,   1    ,  1   , 0.5  ,  0.5   , 0.5 ,   1   ,  0.5  ,    1    ,   2   ,  2   ,  1  ,   2   ,   1   ,   0.5  ,  0.5},  //BICHO
            { 0.5   ,  1   ,   1   ,   2    ,  1   ,  1   ,   1    ,  0  ,   1   ,   1   ,    1    ,   1   ,  1   ,  1  ,   1   ,   1   ,    1   ,   1},   // DRAGÓN
            {  1    ,  2   ,   1   ,  0.5   , 0.5  ,  1   ,   1    ,  1  ,   1   ,   1   ,    1    ,  0.5  ,  1   ,  1  ,   1   ,   0   ,    1   ,   2},   // ELÉCTRICO
            {  1    ,  1   ,   1   ,   1    ,  1   ,  2   ,   1    ,  1  ,   1   ,   1   ,    0    ,   1   ,  2   ,  1  ,  0.5  ,   1   ,    1   ,   1},   // FANTASMA
            {  2    , 0.5  ,   2   ,  0.5   ,  1   ,  1   ,  0.5   ,  1  ,   2   ,   1   ,    1    ,   2   ,  1   , 0.5 ,   1   ,   1   ,    1   ,   1},   // FUEGO
            { 0.5   ,  1   ,   1   ,   2    ,  1   ,  1   ,  0.5   ,  1  ,   1   ,   2   ,    1    ,   1   ,  1   ,  1  ,   2   ,   1   ,   0.5  ,   1},   // HADA
            { 0.5   , 0.5  ,   1   ,   2    ,  1   ,  1   ,  0.5   ,  1  ,  0.5  ,   1   ,    1    ,   2   ,  1   ,  1  ,   1   ,   2   ,    1   ,   2},   // HIELO
            {  2    ,  1   ,  0.5  ,   1    ,  1   ,  0   ,   1    , 0.5 ,   2   ,   1   ,    2    ,   1   , 0.5  ,  2  ,   2   ,   1   ,   0.5  ,  0.5},  // LUCHA
            { 0.5   ,  1   ,   1   ,   1    ,  1   ,  0   ,   1    ,  1  ,   1   ,   1   ,    1    ,   1   ,  1   , 0.5 ,   1   ,   1   ,    1   ,   1},   // NORMAL
            { 0.5   ,  2   ,  0.5  ,  0.5   ,  1   ,  1   ,  0.5   ,  1  ,   1   ,   1   ,    1    ,  0.5  ,  1   ,  2  ,   1   ,   2   ,   0.5  ,  0.5},  // PLANTA
            { 0.5   ,  1   ,   1   ,   1    ,  1   ,  1   ,   1    ,  1  ,   1   ,   2   ,    1    ,   1   , 0.5  ,  1  ,   0   ,   1   ,    2   ,   1},   // PSÍQUICO
            { 0.5   ,  1   ,   2   ,   1    ,  1   ,  1   ,   2    ,  1  ,   2   ,  0.5  ,    1    ,   1   ,  1   ,  1  ,   1   ,  0.5  ,    1   ,   2},   // ROCA
            {  1    ,  1   ,   1   ,   1    ,  1   ,  2   ,   1    , 0.5 ,   1   ,  0.5  ,    1    ,   1   ,  2   ,  1  ,  0.5  ,   1   ,    1   ,   1},   // SINIESTRO
            {  2    ,  1   ,  0.5  ,   1    ,  2   ,  1   ,   2    ,  1  ,   1   ,   1   ,    1    ,  0.5  ,  1   ,  2  ,   1   ,   1   ,    2   ,   0},   // TIERRA
            {  0    ,  1   ,   1   ,   1    ,  1   , 0.5  ,   1    ,  2  ,   1   ,   1   ,    1    ,   2   ,  1   , 0.5 ,   1   ,  0.5  ,   0.5  ,   1},   // VENENO
            { 0.5   ,  1   ,   2   ,   1    , 0.5  ,  1   ,   1    ,  1  ,   1   ,   2   ,    1    ,   2   ,  1   , 0.5 ,   1   ,   1   ,    1   ,   1}    // VOLADOR
    };




}
