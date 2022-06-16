import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess


fun main() {
    principalMenu()
}

fun principalMenu(){
    var optionMenu1: Int = 0
    while (optionMenu1 != 3 ) {
        println("\nPrincipal Menu")
		println("1) All Bibliotecs")
        println("2) All Books")
        println("3) EXIT")
        print("\nChoose -> ")
        optionMenu1 = Integer.parseInt(readLine())

        when (optionMenu1) {
            1 -> {
                BibliotecMenu()
            }
            2 -> {

            }
            3 -> {
                exitProcess(0)
            }
        }
    }
}
//Bibliotecas
fun BibliotecMenu(){
    var optionMenu2: Int = 0

    println("\n\nLIST OF BIBLIOTEC")
    printArray("BibliotecList","Bibliotec","",null)
    println()

    while (optionMenu2 != 6) {
        println("\nBibliotec Menu\n1) Add a new Bibliotec")
        println("2) More inf. about one Bibliotec")
        println("3) Update data from a Bibliotec")
        println("4) Delete a Bibliotec")
        println("5) Books")
        println("6) Back to Principal Menu")
        print("\nChoose -> ")
        optionMenu2 = Integer.parseInt(readLine())

        when (optionMenu2){
            1 -> {
                println("Fill the following inf. about the Bibliotec")
                println("Name of the Bibliotec")
                var name: String = ""
                name = readLine().toString()
                println("Year of fundation")
                var year = ""
                year = readLine().toString()
                println("Country")
                var country = ""
                country = readLine().toString()
                println("Bibliotec address")
                var address = ""
                address = readLine().toString()
                println("Bibliotec Number")
                var number = ""
                number = readLine().toString()
                val BibliotecToAdd = CrudBibliotec(name,year,country,address,number)
                BibliotecToAdd.addBibliotecInfo()

                println("Bibliotec added succsfully!!")
                println("\n\nLIST OF Bibliotec")
                printArray("BibliotecList","Bibliotec","",null)
                println()
            }
            2 -> {
                println("\nUse the number of the Bibliotec to show more info.")
                print("Choose -> ")
                var BibliotecPosition: Int = Integer.parseInt(readLine())
                printArray("BibliotecInfo","Bibliotec","",BibliotecPosition)
            }
            3 -> {
                println("\nUse the number of the Bibliotec to update the info.")
                print("Choose -> ")
                var BibliotecPosition: Int = Integer.parseInt(readLine())
                printArray("BibliotecInfo","Bibliotec","",BibliotecPosition)
                println("\n\nFill the following inf. about the Bibliotec")
                println("Name of the Bibliotec")
                var name: String = ""
                name = readLine().toString()
                println("Year of fundation")
                var year = ""
                year = readLine().toString()
                println("Country")
                var country = ""
                country = readLine().toString()
                println("Bibliotec address")
                var address = ""
                address = readLine().toString()
                println("Bibliotec Number")
                var number = ""
                number = readLine().toString()
                val BibliotecToUpdate = CrudBibliotec(name,year,country,address,number)
                BibliotecToUpdate.updateInfoBibliotec(BibliotecPosition)

                println("\n\nLIST OF Bibliotec")
                printArray("BibliotecList","Bibliotec","",null)
                println()

            }
            4 -> {
                println("\nUse the number of the Bibliotec to delete a Bibliotec info.")
                print("Choose -> ")
                var BibliotecPosition: Int = Integer.parseInt(readLine())
                print("All the Books of [")
                printArray("BibliotecName","Bibliotec","",BibliotecPosition)
                print("] will be deleted too")
                println("\nPress [y] or [n]")
                var confirmInput: String = readLine().toString()
                if (confirmInput.equals("y")){
                    val BibliotecToDelete = CrudBibliotec(BibliotecPosition)
                    BibliotecToDelete.deleteBibliotec(BibliotecPosition)
                    println("\n\nLIST OF Bibliotec")
                    printArray("BibliotecList","Bibliotec","",null)
                    println()
                }else if (confirmInput.equals("n")){
                    println("\n\nLIST OF Bibliotec")
                    printArray("BibliotecList","Bibliotec","",null)
                    println()
                }
            }
            5 -> {
                println("\nUse the number of the Bibliotec to see all the Books of it")
                print("Choose -> ")
                var BibliotecPosition: Int = Integer.parseInt(readLine())
                var BibliotecName: String = printArray("BibliotecName","Bibliotec","",BibliotecPosition)
                BooksMenu(BibliotecName)

            }
            6 -> {
                principalMenu()
            }
        }

    }
}
//Libros
fun BooksMenu(BibliotecName: String){
    var optionMenu3: Int = 0

    println("\n\nLIST OF BOOKS")
    printArray("BooksList","Books",BibliotecName,null)
    println()

    while (optionMenu3 != 5){
        println("\n"+BibliotecName+" Book's Menu\n1) Add a new Book")
        println("2) More inf. about one Book")
        println("3) Update data from a Book")
        println("4) Delete a Book")
        println("5) Back to Bibliotec's Menu")
        print("\nChoose -> ")
        optionMenu3 = Integer.parseInt(readLine())

        when(optionMenu3){
            1 -> {
                println("Fill the following inf. about the Books")
                println("Name of the Books")
                var name: String = ""
                name = readLine().toString()
                println("Bibliotec")
                var Bibliotec: String = ""
                Bibliotec = readLine().toString()
                println("Autor")
                var autor: String = ""
                autor = readLine().toString()
                println("Year of publication")
                var year : String =""
                year = readLine().toString()
                println("Genre")
                var genre: String = ""
                genre = readLine().toString()

                val BooksToAdd = CrudBooks(name,Bibliotec,autor,year,genre)

                BooksToAdd.addBooks()

                println("Books added succsfully!!")
                println("\n\nLIST OF Books")
                printArray("BooksList","Books",BibliotecName,null)
                println()
            }
            2 -> {
                println("\nUse the number of the Books to show more info.")
                print("Choose -> ")
                var BooksPosition: Int = Integer.parseInt(readLine())
                printArray("BooksInfo","Books",BibliotecName,BooksPosition)
            }
            3 -> {
                println("\nUse the number of the Books to show more info.")
                print("Choose -> ")
                var BooksPosition: Int = Integer.parseInt(readLine())
                printArray("BooksInfo","Books",BibliotecName,BooksPosition)
                println("Fill the following inf. about the Books")
                println("Name of the Books")
                var name: String = ""
                name = readLine().toString()
                println("Bibliotec")
                var Bibliotec: String = ""
                Bibliotec = readLine().toString()
                println("Autor")
                var autor: String = ""
                autor = readLine().toString()
                println("Year of publication")
                var year : String =""
                year = readLine().toString()
                println("Genre")
                var genre: String = ""
                genre = readLine().toString()

                val BooksToUpdate = CrudBooks(name,Bibliotec,autor,year,genre)

                BooksToUpdate.updateBooks(BooksPosition)

                println("\n\nLIST OF Books")
                printArray("BooksList","Books",BibliotecName,null)
                println()
            }
            4 -> {
                println("\nUse the number of the Books to delete it")
                print("Choose -> ")
                var BooksPosition: Int = Integer.parseInt(readLine())
                print("All the Books of [")
                printArray("BooksName","Books", BibliotecName,BooksPosition)
                print("] will be deleted too")
                println("\nPress [y] or [n]")
                var confirmInput: String = readLine().toString()
                if (confirmInput.equals("y")){
                    val BooksToDelete = CrudBooks(BooksPosition)
                    BooksToDelete.deleteBooks(BooksPosition)
                    println("\n\nLIST OF Books")
                    printArray("BooksList","Books",BibliotecName,null)
                    println()
                }else if (confirmInput.equals("n")){
                    println("\n\nLIST OF Books")
                    printArray("BooksList","Books",BibliotecName,null)
                    println()
                }
            }
            5 -> {
                BibliotecMenu()
            }
        }

    }


}

abstract class Bibliotec(
    protected var BibliotecName: String,
    protected var BibliotecYear: String,
    protected var BibliotecCountry: String,
    protected var BibliotecAddress: String,
    protected var BibliotecNumber: String,


    ){
    init {
        println("Inicializar")
    }
}

class CrudBibliotec(
    name: String,
    year: String,
    country: String,
    address: String,
    number: String,


    ) : Bibliotec (
    name,
    year,
    country,
    address,
    number,

    ){
    init {
        this.BibliotecName
        this.BibliotecYear
        this.BibliotecCountry
        this.BibliotecAddress
        this.BibliotecNumber

    }

    constructor(
        position: Int
    ) : this (
        "","","","","",
    ){

    }

    fun addBibliotecInfo(){
        var existingBibliotecInfo = ArrayList<String>()
        existingBibliotecInfo = existingRegister()
        var auxBibliotec: String = BibliotecName+","+BibliotecYear+","+BibliotecCountry+","+BibliotecAddress+","+BibliotecNumber
        existingBibliotecInfo.add(auxBibliotec)

        val dataBibliotecFile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/BibliotecsInfo.txt"
        val myfile = File(dataBibliotecFile)
        myfile.printWriter().use { out ->
            existingBibliotecInfo.forEach{
                out.println(it)
            }
        }
    }

    fun updateInfoBibliotec(position: Int){
        var existingBibliotecInfo = ArrayList<String>()
        existingBibliotecInfo = existingRegister()
        var auxPos = position-1
        var auxBibliotec: String = BibliotecName+","+BibliotecYear+","+BibliotecCountry+","+BibliotecAddress+","+BibliotecNumber
        existingBibliotecInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index == auxPos){
                existingBibliotecInfo[index] = auxBibliotec
            }
        }
        val dataBibliotecFile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/BibliotecsInfo.txt"
        val myfile = File(dataBibliotecFile)
        myfile.printWriter().use { out ->
            existingBibliotecInfo.forEach{
                out.println(it)
            }
        }
    }

    fun deleteBibliotec(position: Int){
        var existingBibliotecInfo = ArrayList<String>()
        var existingBibliotecInfoAux = ArrayList<String>()
        existingBibliotecInfo = existingRegister()
        var auxPos = position-1
        existingBibliotecInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index != auxPos){
                existingBibliotecInfoAux.add(existingBibliotecInfo[index])
            }
        }

        val dataBibliotecFile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/BibliotecsInfo.txt"
        val myfile = File(dataBibliotecFile)
        myfile.printWriter().use { out ->
            existingBibliotecInfoAux.forEach{
                out.println(it)
            }
        }
    }


    companion object{

        var existingBooks = ArrayList<String>()

        fun existingRegister () : ArrayList<String> {
            var existingBibliotecList = ArrayList<String>()
            val inputStream: InputStream = File("C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/BibliotecsInfo.txt").inputStream()
            val lineList = mutableListOf<String>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
            lineList.forEach{
                existingBibliotecList.add(it)
            }
            return existingBibliotecList
        }
    }
}

abstract class Books(
    protected var BooksName: String,
    protected var BooksBibliotec: String,
    protected var BooksAutor: String,
    protected var BooksYear: String,
    protected var BooksGenre: String,

    ){
    init {
        println("Inicializar")
    }
}
class CrudBooks(
    name: String,
    Bibliotec: String,
    autor: String,
    year: String,
    genre: String,

    ) : Books (
    name,
    Bibliotec,
    autor,
    year,
    genre,

    ){
    init {
        this.BooksName
        this.BooksBibliotec
        this.BooksAutor
        this.BooksYear
        this.BooksGenre
    }

    constructor(
        position: Int
    ) : this (
        "","","","",""
    ){

    }

    fun addBooks(){
        var existingBooksInfo = ArrayList<String>()
        existingBooksInfo = existingRegisterBooks()
        var auxBooks: String = BooksName+","+BooksBibliotec+","+BooksAutor+","+BooksYear+","+BooksGenre
        existingBooksInfo.add(auxBooks)

        val dataBooksFile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/Books.txt"
        val myfile = File(dataBooksFile)
        myfile.printWriter().use { out ->
            existingBooksInfo.forEach{
                out.println(it)
            }
        }
    }

    fun updateBooks(position: Int){
        var existingBooksInfo = ArrayList<String>()
        existingBooksInfo = existingRegisterBooks()
        var auxPos = position-1
        var auxBooks: String = BooksName+","+BooksBibliotec+","+BooksAutor+","+BooksYear+","+BooksGenre
        existingBooksInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index == auxPos){
                existingBooksInfo[index] = auxBooks
            }
        }
        val dataBooksile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/Books.txt"
        val myfile = File(dataBooksile)
        myfile.printWriter().use { out ->
            existingBooksInfo.forEach{
                out.println(it)
            }
        }
    }

    fun deleteBooks(position: Int){
        var existingBooksInfo = ArrayList<String>()
        var existingBooksInfoAux = ArrayList<String>()
        existingBooksInfo = existingRegisterBooks()
        var auxPos = position-1
        existingBooksInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index != auxPos){
                existingBooksInfoAux.add(existingBooksInfo[index])
            }
        }

        val dataBooksFile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/Books.txt"
        val myfile = File(dataBooksFile)
        myfile.printWriter().use { out ->
            existingBooksInfoAux.forEach{
                out.println(it)
            }
        }
    }


    companion object{

        fun existingRegisterBooks () : ArrayList<String> {
            var existingBooksList = ArrayList<String>()
            val inputStream: InputStream = File("C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/Books.txt").inputStream()
            val lineList = mutableListOf<String>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
            lineList.forEach{
                existingBooksList.add(it)
            }
            return existingBooksList
        }
    }
}


fun printArray(whatToPrint: String, entity: String, BooksBibliotec : String, position: Int?) : String {
    var returnValue = ""
    var phatFile = "C:/Users/marlon.pachacama/Downloads/GitHub/mc-pachacama-granda-marlon-israel/Libro-Biblioteca/"
    if (entity.equals("Bibliotec")){
        phatFile = phatFile.plus("BibliotecsInfo.txt")
    }else if (entity.equals("Books")){
        phatFile = phatFile.plus("Books.txt")
    }
    var inputStream: InputStream = File(phatFile).inputStream()
    val lineList = mutableListOf<String>()
    var auxPos = position?.minus(1)

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
    if (position != null && BooksBibliotec.equals("")){
        lineList.forEachIndexed { index: Int, actualValue: String ->
            if (auxPos == index){
                returnValue = tokenizer(index,actualValue,whatToPrint,"")
            }
        }
    }else if(!BooksBibliotec.equals("") && position == null){
        lineList.forEachIndexed { index: Int, actualValue: String ->
            tokenizer(index,actualValue,whatToPrint,BooksBibliotec)
        }
    }else if(!BooksBibliotec.equals("") && position != null){
        lineList.forEachIndexed { index: Int, actualValue: String ->
            if (auxPos == index){
                returnValue = tokenizer(index,actualValue,whatToPrint,BooksBibliotec)
            }
        }
    } else{
        lineList.forEachIndexed { index: Int, actualValue: String ->
            tokenizer(index,actualValue,whatToPrint,"")
        }
    }
    return  returnValue

}

fun tokenizer(index:Int, BibliotecInfo : String, whatToPrint: String, BooksBibliotec: String) : String{
    var returnValue: String = ""
    var listBibliotec = ArrayList<String>()
    val st = StringTokenizer(BibliotecInfo,",")
    val auxIndex = index+1
    while (st.hasMoreTokens()) {
        listBibliotec.add(st.nextToken())
    }
    if (whatToPrint.equals("BibliotecInfo")){
        print(auxIndex)
        print("\t")
        listBibliotec.forEach{
            print(it+"\t")
        }
        println("\n")
    }
    else if (whatToPrint.equals("BibliotecList")) {
        print(auxIndex)
        print("\t")
        print(listBibliotec[0])
        println()
    }else if (whatToPrint.equals("BibliotecName")) {
        returnValue = listBibliotec[0]
        print(listBibliotec[0])
    }else if (whatToPrint.equals("BooksInfo")){
        if (listBibliotec[1].equals(BooksBibliotec)) {
            print(auxIndex)
            print("\t")
            listBibliotec.forEach {
                print(it + "\t")
            }
            println("\n")
        }
    } else if (whatToPrint.equals("BooksList")){
        if (listBibliotec[1].equals(BooksBibliotec)){
            print(auxIndex)
            print("\t")
            print(listBibliotec[0])
            println()
        }
    }else if (whatToPrint.equals("BooksName")) {
        if (listBibliotec[1].equals(BooksBibliotec)){
            returnValue = listBibliotec[0]
            print(listBibliotec[0])
        }
    }
    return returnValue
}