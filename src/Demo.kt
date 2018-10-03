import kotlin.reflect.KProperty

class NameDelegate { //class that handles naming schemes
  
  var name:String = "nobody" //extra data about the name
  var askCount = 0

  operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
    askCount++ //keep track of who has accessed
    return if(askCount >= 3) "I've already told you" else name
  }

  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
    name = value.toUpperCase() //save modified version
  }
}

class Dog(name:String) {
  var name:String by NameDelegate() //delegate the property
  init {
    this.name = name //(initialize it though)
  }
}

fun main(args: Array<String>) {
val dog:Dog = Dog("Fido")
println(dog.name)
dog.name = "Rover"
println(dog.name)
println(dog.name)
}
