import java.lang.NumberFormatException
//Salva dice que limpie la consola(No se si hacerle caso)
class Juego
{
    var tableroJugador = Array(8){Array(8){"~~~"} }
    var tableroIA = Array(8){Array(8){"~~~"} }
    var barcosJugador = List(6){Barco()}
    var barcosIA = List(6){Barco()}

    fun pintarTablero()
    {
        var letter = 65
        println("-------------------------------------------------------------------------------")
        println("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | \t \t  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |")
        for(i in tableroJugador.indices)
        {
            print("|${letter.toChar()}")

            for(x in tableroJugador.indices)
            {
                print("|" + tableroJugador[i][x])
            }
            print("| \t \t")
            print("|${letter.toChar()}")
            letter++
            for(z in tableroIA.indices)
                print("|" + tableroIA[i][z])
            println("|")
        }
        println("-------------------------------------------------------------------------------")
    }
    private fun comprobarBarcos(barcoAColocar : Barco, mapa : Array<Array<String>>) : Boolean{
        var estaOcupado = false
        val coordenadasLetras = mutableListOf<Int>()
        val coordenadasNum = mutableListOf<Int>()

        for(i in 0 until barcoAColocar.tamanoBarco)
        {
            if(barcoAColocar.direccion == 0 && barcoAColocar.coordenadasLetras[0] - i >= 0)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0] - i][barcoAColocar.coordenadasNumeros[0]].compareTo("~~~") !=0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0] - i)
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0])
                }
            }
            else if(barcoAColocar.direccion == 1 && barcoAColocar.coordenadasNumeros[0] + i <=7)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0]][barcoAColocar.coordenadasNumeros[0] + i].compareTo("~~~") != 0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0])
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0] + i)
                }
            }
            else if(barcoAColocar.direccion == 2 && barcoAColocar.coordenadasLetras[0] + i <= 7)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0] + i][barcoAColocar.coordenadasNumeros[0]].compareTo("~~~") != 0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0] + i)
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0])
                }
            }
            else if(barcoAColocar.direccion == 3 && barcoAColocar.coordenadasNumeros[0] - i >= 0)
            {
                if(mapa[barcoAColocar.coordenadasLetras[0]][barcoAColocar.coordenadasNumeros[0] - i].compareTo("~~~") != 0)
                {
                    estaOcupado = true
                    break
                }
                else
                {
                    coordenadasLetras.add(barcoAColocar.coordenadasLetras[0])
                    coordenadasNum.add(barcoAColocar.coordenadasNumeros[0] - i)
                }
            }
            else
            {
                estaOcupado = true
                break
            }
        }
        if(!estaOcupado)
        {
            barcoAColocar.coordenadasLetras = coordenadasLetras
            barcoAColocar.coordenadasNumeros = coordenadasNum
        }
        else{
            barcoAColocar.coordenadasLetras.removeAt(0)
            barcoAColocar.coordenadasNumeros.removeAt(0)
        }

        return estaOcupado
    }

    private fun pintarBarco(barco : Barco, mapa : Array<Array<String>>) : Boolean{
        var faltaAlgo = false
        if(!comprobarBarcos(barco, mapa))
        {
          if(mapa.contentEquals(tableroJugador))
          {
              if (barco.nombre.compareTo("Submarino") == 0)
              {
                  for (x in 0 until barco.tamanoBarco)
                      mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " S "

              }
              else if (barco.nombre.compareTo("Destructor") == 0)
              {
                  for (x in 0 until barco.tamanoBarco)
                      mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " D "
              }
              else if (barco.nombre.compareTo("Crucero") == 0)
              {
                  for (x in 0 until barco.tamanoBarco)
                      mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " C "
              }
              else if (barco.nombre.compareTo("Acorazado") == 0)
              {
                  for (x in 0 until barco.tamanoBarco)
                      mapa[barco.coordenadasLetras[x]][barco.coordenadasNumeros[x]] = " A "
              }
          }
        }
        else
        {
            faltaAlgo = true
        }
        return faltaAlgo
    }

   fun pedirDatos(barco : Barco)
    {
       var condicion = true
        do{
           try{
               println("¿Dónde quieres colocar el ${barco.nombre}, que su tamaño es de ${barco.tamanoBarco}?(La letra)")
               barco.coordenadasLetras.add(comprobarLetra(readln()))
               println("¿Dónde quieres colocar el ${barco.nombre}?(El número)")
               barco.coordenadasNumeros.add(comprobarNumero(readln().toInt()))
               println("¿En qué dirección lo quieres? \n Arriba: W \n Derecha: D \n Abajo: S \n Izquierda: A")
               barco.comprobarDireccion(readln())
               condicion = pintarBarco(barco, tableroJugador)
               if(condicion)
                   println("Ha colocado erroneamente el barco, vuelva a introducir los datos")
           }catch(e : NumberFormatException){
               println("Formato numérico erróneo, volvamos a empezar")
           }
        }while(condicion)
        pintarTablero()

    }

     fun generarBarcosIA(barco : Barco)
    {
        var condicion : Boolean
        do{
            barco.coordenadasLetras.add( (0..7).random())
            barco.coordenadasNumeros.add((0..7).random())
            barco.direccion = (0..4).random()

            condicion = pintarBarco(barco, tableroIA)
        }while(condicion)
    }

   fun atacar(mapa : Array<Array<String>>, barcos : List<Barco>)
    {
       do{
           var coordenadaLetra : Int
           var coordenadaNum : Int
           var tocado = false
           if(mapa.contentEquals(tableroJugador) && barcos == barcosJugador)
           {
               coordenadaLetra = (0..7).random()
               coordenadaNum = (0..7).random()
           }
           else{
               println("¿A qué coordenada quiere atacar?(La letra)")
               coordenadaLetra = comprobarLetra(readln())
               println("¿A qué coordenada quiere atacar?(El número)")
               coordenadaNum = comprobarNumero(readln().toInt())
           }
           try{
               for(i in barcos)
               {
                  if(!tocado)
                  {
                      for(x in 0 until i.coordenadasNumeros.size)
                      {
                          if((i.coordenadasLetras[x] == coordenadaLetra && i.coordenadasNumeros[x] == coordenadaNum) &&
                              (mapa[i.coordenadasLetras[x]][i.coordenadasNumeros[x]] !=" T " && mapa[i.coordenadasLetras[x]][i.coordenadasNumeros[x]] !=" H "))
                          {
                              mapa[coordenadaLetra][coordenadaNum] = " T "
                              tocado = true
                              i.vidas--
                              if(i.vidas == 0)
                              {
                                  for(z in 0 until i.tamanoBarco)
                                  {
                                      mapa[i.coordenadasLetras[z]][i.coordenadasNumeros[z]] = " H "
                                  }
                              }
                              println("¡TIRO ACERTADO, SE AÑADE UN NUEVO TURNO!")
                              pintarTablero()

                              readLine()
                              break
                          }
                      }
                  }
               }
               if(!tocado && mapa[coordenadaLetra][coordenadaNum] == "~~~"){
                   mapa[coordenadaLetra][coordenadaNum] = "***"
               }

               if(!tocado){
                   pintarTablero()
                   println("¡TIRO FALLADO!")
               }
           }catch(e : NumberFormatException){
               println("El formato del número es erróneo, inténtelo de nuevo")
               tocado = false
           }
       }while(tocado)
   }
    private fun comprobarLetra(letra : String) : Int{
        var coordenada = letra.uppercase()
        var coordenadaNum = -1
        do{
            when(coordenada){
                "A" -> coordenadaNum = 0
                "B" -> coordenadaNum = 1
                "C" -> coordenadaNum = 2
                "D" -> coordenadaNum = 3
                "E" -> coordenadaNum = 4
                "F" -> coordenadaNum = 5
                "G" -> coordenadaNum = 6
                "H" -> coordenadaNum = 7
                else ->{
                    println("Letra equivocada, inténtelo de nuevo:")
                    coordenada = readln().uppercase()
                }
            }
        }while(coordenadaNum == -1)
        return coordenadaNum
    }

    private fun comprobarNumero(num : Int) : Int{
        var coordenada = num
        var coordenadaNumero = -1
        do
        {
            try
            {
                if(coordenada in 1..8)
                {
                    coordenadaNumero = coordenada - 1
                }
                else
                {
                    println("Número equivocado, inténtelo de nuevo:")
                    coordenada = readln().toInt()
                }
            }
            catch(e : NumberFormatException)
            {
                println("Formato numérico incorrecto, inténtalo de nuevo")
                coordenada = readln().toInt()
            }
        }while(coordenadaNumero == -1)
        return coordenadaNumero
    }

    fun comprobarGanador() : Boolean
    {
        var contadorVidasJugador = 6
        var contadorVidasIA = 6

        for(element in barcosJugador){
            if(element.vidas == 0)
                contadorVidasJugador--
        }

        for(element in barcosIA){
            if(element.vidas == 0)
                contadorVidasIA--
        }

        return if(contadorVidasJugador == 0){
            println("-------------------------------------------------------------------\n" +
                    "----------------------- ¡GANADOR: JUGADOR! ------------------------\n" +
                    "-------------------------------------------------------------------\n")
            false
        } else if(contadorVidasIA == 0){
            println("-------------------------------------------------------------------\n" +
                    "------------------------- ¡GANADOR: IA! ---------------------------\n" +
                    "-------------------------------------------------------------------\n")
            false
        } else{
            true
        }


    }
}

fun main() {
    val tableroJuego = Juego()
    val nombres = listOf("Submarino", "Submarino", "Destructor", "Destructor", "Crucero", "Acorazado")
    val tamanyos = listOf(1,1,2,2,3,4)

    for(i in nombres.indices){
        tableroJuego.barcosIA[i].tamanoBarco = tamanyos[i]
        tableroJuego.barcosIA[i].nombre = nombres[i]
        tableroJuego.barcosIA[i].vidas = tamanyos[i]

        tableroJuego.barcosJugador[i].tamanoBarco = tamanyos[i]
        tableroJuego.barcosJugador[i].nombre = nombres[i]
        tableroJuego.barcosJugador[i].vidas = tamanyos[i]
    }
    bienvenida()
    reglas()
    Thread.sleep(3000)
    tableroJuego.pintarTablero()

   for(i in tableroJuego.barcosJugador){
    tableroJuego.pedirDatos(i)
   }

    for(i in tableroJuego.barcosIA){
        tableroJuego.generarBarcosIA(i)
    }

    println("----------------------------------------------------------------\n" +
            "------------------------ BARCOS CREADOS ------------------------\n" +
            "-------------------- ¡QUE EMPIECE EL JUEGO! ---------------------\n" +
            "-----------------------------------------------------------------")
    var seguirJugando : Boolean
    do
    {
        println("¡TURNO DE LA IA! PULSA \"INTRO\" PARA CONTINUAR")
        readLine()
        tableroJuego.atacar(tableroJuego.tableroJugador,tableroJuego.barcosJugador)
        println("¡TURNO DEL JUGADOR! PULSA \"INTRO\" PARA CONTINUAR")
        readLine()
        tableroJuego.atacar(tableroJuego.tableroIA,tableroJuego.barcosIA)
        seguirJugando = tableroJuego.comprobarGanador()
    }
    while(seguirJugando)

}
fun bienvenida(){
    println("----------------------------------------------------------- \n" +
            "--------------------BIENVENIDO AL JUEGO--------------------\n" +
            "-----------------------------------------------------------\n-\n-\n-"
            )

}

fun reglas()
{
    println("REGLAS DEL JUEGO \n" +
            "1. Elimina todos los barcos del enemigo \n" +
            "2. Primero colocarás tus 6 barcos, después se generarán los barcos de la IA \n" +
            "3. ¡No repitas una coordenada, fallarás y perderás el turno!\n" +
            "4. La IA ataque aleatoriamente, ¡aun así ten cuidado!\n" +
            "5. La última pero no menos importante, ¡DISFRUTA!\n" +
            " Pulsa \"Intro\" para continuar")
}


