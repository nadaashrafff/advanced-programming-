import scala.io.Source
import scala.io.StdIn
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

val all_syn = List("stars","egp","liters","gears","kg","mph","kmph","view","add","recommendation", "recommend", "suggestion", "suggest", "prefer", "elaborate", "don't", "thanks", "explain", "thanks!", "dealership", "dealer", "china", "us", "japan", "france", "spain", "italy", "germany", "uk", "korea", "fwd", "4wd", "rwd", "awd", "na", "supercharged", "turbocharged", "manual", "automatic", "v", "flat", "w", "inline", "coupe", "sedan", "hatchback", "convertible", "suv", "gasoline", "diesel", "electric", "hybrid", "cylinders", "doors", "price", "cost", "value", "pricing", "rate", "expense", "amount", "payment", "worth", "cc", "horsepower", "hp", "acceleration", "speed", "accelerate", "seats", "cargo", "safety", "infotainment", "entertainment", "audio", "media", "multimedia", "sunroof", "moonroof", "navigation", "gps", "torque", "twist", "moment", "ford", "aptera", "dodge", "pontiac", "toyota", "kia", "mercedes-benz", "acura", "hyundai", "mazda", "chevrolet", "infiniti", "mitsubishi", "lexus", "jeep", "jaguar", "volvo", "audi", "ferrari", "gmc", "eagle", "mercury", "suzuki", "isuzu", "subaru", "buick", "fiat", "scion", "maybach", "cadillac", "nissan", "plymouth", "mini", "volkswagen", "bmw", "land rover", "aston martin", "honda", "lamborghini", "oldsmobile", "hummer", "saab", "chrysler", "geo", "daewoo", "spyker", "alfa romeo", "porsche", "bentley", "tesla", "lotus", "edge", "typ-1", "dakota", "gto", "explorer", "camry", "optima", "slk-class", "nsx", "elantra", "rx-7", "rx-8", "cobalt ss", "q", "eclipse", "traverse", "celica", "thunderbird", "sc", "grand cherokee", "4runner", "xk series", "suburban 1500", "c70", "cabriolet", "s70", "612 scaglietti", "suburban 2500", "eldorado", "equator", "axiom", "baja", "forenza", "envoy xl", "vision", "cougar", "caravan", "l300", "g-series 1500", "sportage", "s4", "skylark", "500", "swift", "s60", "mdx", "talon", "murano", "ram 1500", "ltd crown victoria", "diamante", "catera", "highlander", "solara", "tercel", "carens", "volare", "journey", "ram van 3500", "s-class", "camry solara", "d350", "entourage", "xb", "grand vitara", "lynx", "aerostar", "57", "yukon", "monte carlo", "uplander", "express 2500", "protege", "crown victoria", "astro", "rl", "passat", "fiesta", "fairlane", "savana 3500", "beretta", "fusion", "gran sport", "silverado 1500", "cls-class", "m-class", "patriot", "s40", "dbs", "civic", "murciélago", "outback", "6000", "montero", "taurus", "cl-class", "impala", "7 series", "b-series plus", "500sel", "avenger", "expedition", "galant", "cayman", "altima", "excel", "s80", "estate", "viper", "prizm", "clubman", "cooper", "62", "xj", "yukon xl 1500", "familia", "is f", "touareg", "montana", "850", "outlook", "mark viii", "3000gt", "accord", "bronco ii", "intrigue", "wrangler", "1500", "dakota club", "h1", "e150", "qx", "econoline e150", "liberty", "sonata", "miata mx-5", "durango", "esteem", "evora", "expedition el", "equinox", "new yorker", "tsx", "525", "truck", "xterra", "lemans", "scoupe", "esprit", "rodeo", "911", "s-type", "blazer", "sprinter", "stealth", "maxima", "countach", "passport", "ranger", "flex", "golf iii", "jetta iii", "f-250 super duty", "sierra", "09-mar", "mr2", "h3", "brat", "fox", "xc70", "rvr", "lr2", "xc60", "90", "impreza", "spectra", "rio", "discovery series ii", "integra", "venture", "outlander sport", "tiburon", "sorento", "ex", "200sx", "space", "milan", "defender", "relay", "ram van b250", "pathfinder", "300e", "960", "e-350 super duty", "370z", "quattroporte", "fit", "cx-9", "pilot", "f350", "sedona", "gs", "s5", "regency", "g55 amg", "blackwood", "g-series g10", "300sl", "ram 50", "745", "breeze", "vigor", "is", "montana sv6", "compass", "e-250", "g35", "d250", "falcon", "aveo")
val Fuel_tank_capacity= List("liters")
val number_of_gears= List("gears")
val car_weight = List("kg")
val car_top_speed= List("mph","kmph")
val car_price_syn = List("price", "cost", "value", "pricing", "rate", "expense", "amount", "payment", "worth","egp")
val car_engine_size_syn = List("cc")
val car_horsepower_syn = List("horsepower", "hp")
val car_acceleration_0_60mph_syn = List("acceleration",  "speed", "accelerate")
val car_seating_capacity_syn = List("seats")
val car_cargo_capacity_syn = List("cargo")
val car_safety_rating_syn = List("safety","stars")
val car_infotainment_system_syn = List("infotainment", "entertainment ", "audio ", "media ",  " entertainment", "multimedia")
val car_sunroof_syn = List("sunroof", "moonroof",  "skylight")
val car_gps_navigation_syn = List( "navigation ", "gps")
val car_torque_syn = List("torque", "twist", "moment")
val number_of_doors_syn = List("doors")
val number_of_cylinders_syn = List("cylinders")
val carMakes = List("ford", "aptera", "dodge", "pontiac", "toyota", "kia", "mercedes-benz", "acura", "hyundai", "mazda", "chevrolet", "infiniti", "mitsubishi", "lexus", "jeep", "jaguar", "volvo", "audi", "ferrari", "gmc", "eagle", "mercury", "suzuki", "isuzu", "subaru", "buick", "fiat", "scion", "maybach", "cadillac", "nissan", "plymouth", "mini", "volkswagen", "bmw", "land rover", "aston martin", "honda", "lamborghini", "oldsmobile", "hummer", "saab", "chrysler", "geo", "daewoo", "spyker", "alfa romeo", "porsche", "bentley", "tesla", "lotus", "aston martin", "mercedes-benz", "subaru", "alfa romeo", "bmw", "chevrolet", "bentley", "alfa romeo", "spyker", "alfa romeo")
val carModels = List("edge", "typ-1", "dakota", "gto", "explorer", "camry", "optima", "slk-class", "nsx", "elantra", "rx-7", "rx-8", "cobalt ss", "q", "eclipse", "traverse", "celica", "thunderbird", "sc", "grand cherokee", "4runner", "xk series", "suburban 1500", "c70", "cabriolet", "s70", "612 scaglietti", "suburban 2500", "eldorado", "equator", "axiom", "baja", "forenza", "envoy xl", "vision", "cougar", "caravan", "l300", "g-series 1500", "sportage", "s4", "skylark", "500", "swift", "s60", "mdx", "talon", "murano", "ram 1500", "ltd crown victoria", "diamante", "catera", "highlander", "solara", "tercel", "carens", "volare", "journey", "ram van 3500", "s-class", "camry solara", "d350", "entourage", "xb", "grand vitara", "lynx", "aerostar", "57", "yukon", "monte carlo", "uplander", "express 2500", "protege", "crown victoria", "astro", "rl", "passat", "fiesta", "fairlane", "savana 3500", "beretta", "fusion", "gran sport", "silverado 1500", "cls-class", "m-class", "patriot", "s40", "dbs", "civic", "murciélago", "outback", "6000", "montero", "taurus", "cl-class", "impala", "7 series", "b-series plus", "500sel", "avenger", "expedition", "galant", "cayman", "altima", "excel", "s80", "estate", "viper", "prizm", "clubman", "cooper", "62", "xj", "yukon xl 1500", "familia", "is f", "touareg", "montana", "850", "b-series", "outlook", "mark viii", "3000gt", "accord", "bronco ii", "intrigue", "wrangler", "1500", "dakota club", "h1", "e150", "qx", "econoline e150", "liberty", "sonata", "miata mx-5", "durango", "esteem", "evora", "expedition el", "eclipse", "equinox", "new yorker", "tsx", "525", "truck", "xterra", "lemans", "scoupe", "esprit", "rodeo", "911", "s-type", "blazer", "sprinter", "stealth", "maxima", "countach", "passport", "ranger", "flex", "golf iii", "jetta iii", "f-250 super duty", "sierra", "09-mar", "mr2", "h3", "brat", "fox", "xc70", "rvr", "lr2", "xc60", "90", "impreza", "spectra", "rio", "discovery series ii", "integra", "venture", "b-series", "outlander sport", "passat", "tiburon", "sorento", "ex", "200sx", "space", "milan", "defender", "relay", "ram van b250", "pathfinder", "300e", "960", "e-350 super duty", "370z", "quattroporte", "fit", "cx-9", "pilot", "f350", "sedona", "gs", "s5", "regency", "g55 amg", "blackwood", "g5", "slr mclaren", "allante", "g-series g10", "300sl", "gto", "ram 50", "745", "breeze", "vigor", "is", "montana sv6", "compass", "e-250", "g35", "d250", "falcon", "aveo", "defender ice edition")
val colors = List("crimson", "red", "puce", "khaki", "orange", "pink", "indigo", "mauv", "yellow", "teal", "blue", "maroon", "aquamarine", "violet", "fuscia", "goldenrod", "turquoise", "purple", "green")
val fuelTypes = List("gasoline", "diesel", "electric", "hybrid")
val bodyTypes = List("coupe", "sedan", "hatchback", "convertible", "suv")
val cylinderLayout = List("v", "flat", "w", "inline")
val gearboxType = List("manual", "automatic")
val boostType = List("na", "supercharged", "turbocharged")
val driveTrain = List("fwd", "4wd", "rwd", "awd")
val originCountry = List("china", "us", "japan", "france", "spain", "italy", "germany", "uk", "korea")
val car_dealership_syn = List("dealership", "dealer")
val recommendation_phrases_syn = List("recommendation", "recommend", "suggestion", "suggest")
val preference_phrases_syn = List("prefer")
val negation_refusal_phrases_syn = List("don't","hate")
val acceptance_approval_phrases_syn = List("view", "yes")
val rwants_syn = List("want","like")
val clarification_phrases_syn = List("explain")
val gratitude_politeness_phrases_syn = List("thanks!")
val allCars = List("gears","kg","mph","kmph","dealership", "dealer", "china", "us", "japan", "france", "spain", "italy", "germany", "uk", "korea", "fwd", "4wd", "rwd", "awd", "na", "supercharged", "turbocharged", "manual", "automatic", "v", "flat", "w", "inline", "coupe", "sedan", "hatchback", "convertible", "suv", "gasoline", "diesel", "electric", "hybrid", "cylinders", "doors", "price", "cost", "value", "pricing", "rate", "expense", "amount", "payment", "worth", "cc", "horsepower", "hp", "acceleration", "speed", "accelerate", "seats", "cargo", "safety", "infotainment", "entertainment", "audio", "media", "entertainment", "multimedia", "sunroof", "moonroof", "skylight", "navigation", "gps", "torque", "twist", "moment", "ford", "aptera", "dodge", "pontiac", "toyota", "kia", "mercedes-benz", "acura", "hyundai", "mazda", "chevrolet", "infiniti", "mitsubishi", "lexus", "jeep", "jaguar", "volvo", "audi", "ferrari", "gmc", "eagle", "mercury", "suzuki", "isuzu", "subaru", "buick", "fiat", "scion", "maybach", "cadillac", "nissan", "plymouth", "mini", "volkswagen", "bmw", "land rover", "aston martin", "honda", "lamborghini", "oldsmobile", "hummer", "saab", "chrysler", "geo", "daewoo", "spyker", "alfa romeo", "porsche", "bentley", "tesla", "lotus", "aston martin", "mercedes-benz", "subaru", "alfa romeo", "bmw", "chevrolet", "bentley", "alfa romeo", "spyker", "alfa romeo", "edge", "typ-1", "dakota", "gto", "explorer", "camry", "optima", "slk-class", "nsx", "elantra", "rx-7", "rx-8", "cobalt ss", "q", "eclipse", "traverse", "celica", "thunderbird", "sc", "grand cherokee", "4runner", "xk series", "suburban 1500", "c70", "cabriolet", "s70", "612 scaglietti", "suburban 2500", "eldorado", "equator", "axiom", "baja", "forenza", "envoy xl", "vision", "cougar", "caravan", "l300", "g-series 1500", "sportage", "s4", "skylark", "500", "swift", "s60", "mdx", "talon", "murano", "ram 1500", "ltd crown victoria", "diamante", "catera", "highlander", "solara", "tercel", "carens", "volare", "journey", "ram van 3500", "s-class", "camry solara", "d350", "entourage", "xb", "grand vitara", "lynx", "aerostar", "57", "yukon", "monte carlo", "uplander", "express 2500", "protege", "crown victoria", "astro", "rl", "passat", "fiesta", "fairlane", "savana 3500", "beretta", "fusion", "gran sport", "silverado 1500", "cls-class", "m-class", "patriot", "s40", "dbs", "civic", "murciélago", "outback", "6000", "montero", "taurus", "cl-class", "impala", "7 series", "b-series plus", "500sel", "avenger", "expedition", "galant", "cayman", "altima", "excel", "s80", "estate", "viper", "prizm", "clubman", "cooper", "62", "xj", "yukon xl 1500", "familia", "is f", "touareg", "montana", "850", "b-series", "outlook", "mark viii", "3000gt", "accord", "bronco ii", "intrigue", "wrangler", "1500", "dakota club", "h1", "e150", "qx", "econoline e150", "liberty", "sonata", "miata mx-5", "durango", "esteem", "evora", "expedition el", "eclipse", "equinox", "new yorker", "tsx", "525", "truck", "xterra", "lemans", "scoupe", "esprit", "rodeo", "911", "s-type", "blazer", "sprinter", "stealth", "maxima", "countach", "passport", "ranger", "flex", "golf iii", "jetta iii", "f-250 super duty", "sierra", "09-mar", "mr2", "h3", "brat", "fox", "xc70", "rvr", "lr2", "xc60", "90", "impreza", "spectra", "rio", "discovery series ii", "integra", "venture", "b-series", "outlander sport", "passat", "tiburon", "sorento", "ex", "200sx", "space", "milan", "defender", "relay", "ram van b250", "pathfinder", "300e", "960", "e-350 super duty", "370z", "quattroporte", "fit", "cx-9", "pilot", "f350", "sedona", "gs", "s5", "regency", "g55 amg", "blackwood", "g5", "slr mclaren", "allante", "g-series g10", "300sl", "gto", "ram 50", "745", "breeze", "vigor", "is", "montana sv6", "compass", "e-250", "g35", "d250", "falcon", "aveo", "defender ice edition")
val all_input = List("egp","recommend", "suggestion", "suggest", "prefer", "elaborate", "don't", "thanks", "explain", "thanks!")
val list = List("car_make", "car_model", "car_color", "car_price", "car_fuel_type", "car_engine_size", "car_horsepower", "car_top_speed", "car_acceleration", "body_type", "car_seating_capacity", "car_cargo_capacity", "car_safety_rating", "car_infotainment_system", "car_sunroof", "car_gps_navigation", "car_torque", "number_of_doors", "car_weight", "boost_type", "cylinder_layout", "number_of_cylinders", "gearbox_type", "number_of_gears", "drive_train", "fuel_tank_capacity", "origin_country", "car_dealership")
var add = List("add")

case class Car(
                carId: String, make: String, model: String, color: String, price: Double, fuelType: String,
                engineSize: Double, horsepower: Int, acceleration: Double, bodyType: String, seatingCapacity: Int,
                cargoCapacity: Double, safetyRating: Double, infotainmentSystem: Boolean, sunroof: Boolean,
                gpsNavigation: Boolean, torque: Int, numberOfDoors: Int, weight: Double, boostType: String,
                cylinderLayout: String, numberOfCylinders: Int, gearboxType: String, numberOfGears: Int,
                driveTrain: String, fuelTankCapacity: Double, originCountry: String, dealership: String
              )


case class Preference(
                       car_make: Option[String] = None,
                       car_model: Option[String] = None,
                       car_color: Option[String] = None,
                       car_price: Option[Double] = None,
                       car_engine_size: Option[Double] = None,
                       car_horsepower: Option[Int] = None,
                       car_acceleration_0_60mph: Option[Double] = None,
                       car_seating_capacity: Option[Int] = None,
                       car_cargo_capacity: Option[Double] = None,
                       car_safety_rating: Option[Int] = None,
                       car_infotainment_system: Option[String] = None,
                       car_sunroof: Option[Boolean] = None,
                       car_gps_navigation: Option[Boolean] = None,
                       car_torque: Option[Int] = None,
                       number_of_doors: Option[Int] = None,
                       number_of_cylinders: Option[Int] = None,
                       drive_train: Option[String] = None
                     )



def loadCarsFromCSV(filePath: String): Array[Car] = {
  val reader = CSVReader.open(new File(filePath))
  try {
    (for {
      fields <- reader.allWithHeaders()
    } yield {
      Car(
        carId = fields("carId"),
        make = fields("make"),
        model = fields("model"),
        color = fields("color"),
        price = fields("price").toDouble,
        fuelType = fields("fuelType"),
        engineSize = fields("engineSize").toDouble,
        horsepower = fields("horsepower").toInt,
        acceleration = fields("acceleration").toDouble,
        bodyType = fields("bodyType"),
        seatingCapacity = fields("seatingCapacity").toInt,
        cargoCapacity = fields("cargoCapacity").toDouble,
        safetyRating = fields("safetyRating").toDouble,
        infotainmentSystem = fields("infotainmentSystem").toBoolean,
        sunroof = fields("sunroof").toBoolean,
        gpsNavigation = fields("gpsNavigation").toBoolean,
        torque = fields("torque").toInt,
        numberOfDoors = fields("numberOfDoors").toInt,
        weight = fields("weight").toDouble,
        boostType = fields("boostType"),
        cylinderLayout = fields("cylinderLayout"),
        numberOfCylinders = fields("numberOfCylinders").toInt,
        gearboxType = fields("gearboxType"),
        numberOfGears = fields("numberOfGears").toInt,
        driveTrain = fields("driveTrain"),
        fuelTankCapacity = fields("fuelTankCapacity").toDouble,
        originCountry = fields("originCountry"),
        dealership = fields("dealership")
      )
    }).toArray
  } finally {
    reader.close()
  }
}


def parseInput(input: String): List[String] = {
  input.toLowerCase.split("\\s+").filter(all_syn.contains).toList
}


def findTokenBefore(sentence: String, target: String): Option[String] = {
  val words = sentence.split("\\s+")
  val index = words.indexOf(target)
  if (index > 0) Some(words(index - 1)) else None
}

def findTokenAfter(sentence: String, target: String): Option[String] = {
  val words = sentence.split("\\s+")
  val index = words.indexOf(target)
  if (index >= 0 && index < words.length - 1) Some(words(index + 1)) else None
}


def storeUserPreferences(tokens: List[String], sentence: String): Preference = {
  tokens.foldLeft(Preference())((prefs, token) => {
    token match {

      case t if car_price_syn.contains(t) => findTokenAfter(sentence, t).flatMap(_.toDoubleOption).map(price => prefs.copy(car_price = Some(price))).getOrElse(prefs)
      case t if car_horsepower_syn.contains(t) => findTokenBefore(sentence, t).flatMap(_.toIntOption).map(hp => prefs.copy(car_horsepower = Some(hp))).getOrElse(prefs)
      case t if car_engine_size_syn.contains(t) => findTokenAfter(sentence, t).flatMap(_.toDoubleOption).map(engine => prefs.copy(car_engine_size = Some(engine))).getOrElse(prefs)
      case t if car_acceleration_0_60mph_syn.contains(t) => findTokenAfter(sentence, t).flatMap(_.toDoubleOption).map(accel => prefs.copy(car_acceleration_0_60mph = Some(accel))).getOrElse(prefs)
      case t if car_seating_capacity_syn.contains(t) => findTokenBefore(sentence, t).flatMap(_.toIntOption).map(seats => prefs.copy(car_seating_capacity = Some(seats))).getOrElse(prefs)
      case t if number_of_doors_syn.contains(t) => findTokenBefore(sentence, t).flatMap(_.toIntOption).map(doors => prefs.copy(number_of_doors = Some(doors))).getOrElse(prefs)
      case t if number_of_cylinders_syn.contains(t) => findTokenBefore(sentence, t).flatMap(_.toIntOption).map(cyl => prefs.copy(number_of_cylinders = Some(cyl))).getOrElse(prefs)
      case t if car_torque_syn.contains(t) => findTokenAfter(sentence, t).flatMap(_.toIntOption).map(torque => prefs.copy(car_torque = Some(torque))).getOrElse(prefs)
      case _ => prefs
    }
  })
}


def findMatch(cars: Array[Car], preferences: Preference): Array[Car] = {
  cars.filter { car =>
    List(
      preferences.car_make.map(_ == car.make),
      preferences.car_model.map(_ == car.model),
      preferences.car_color.map(_ == car.color),
      preferences.car_price.map(car.price <= _),
      preferences.car_engine_size.map(car.engineSize <= _),
      preferences.car_horsepower.map(car.horsepower >= _),
      preferences.car_acceleration_0_60mph.map(car.acceleration <= _),
      preferences.car_seating_capacity.map(car.seatingCapacity == _),
      preferences.car_cargo_capacity.map(car.cargoCapacity <= _),
      preferences.car_safety_rating.map(car.safetyRating >= _),
      preferences.car_infotainment_system.map(car.infotainmentSystem.toString == _),
      preferences.car_sunroof.map(car.sunroof == _),
      preferences.car_gps_navigation.map(car.gpsNavigation == _),
      preferences.car_torque.map(car.torque >= _),
      preferences.number_of_doors.map(car.numberOfDoors == _),
      preferences.number_of_cylinders.map(car.numberOfCylinders == _),
      preferences.drive_train.map(car.driveTrain == _)
    ).flatten.forall(identity) // Only include cars where all defined preferences are true
  }
}

def greetUser(): String = {
  "Welcome to car recommendation chat bot. How may I help you?"
}

def specsexist(input: List[String], sentence: String): List[String] = {
  val intersection = input.flatMap {
    case word if carMakes.contains(word) =>List(word)
    case word if carModels.contains(word) => List(word)
    case word if colors.contains(word) => List(word)
    case word if fuelTypes.contains(word) => List(word)
    case word if bodyTypes.contains(word) => List(word)
    case word if cylinderLayout.contains(word) => List(word)
    case word if gearboxType.contains(word) => List(word)
    case word if boostType.contains(word) => List(word)
    case word if driveTrain.contains(word) => List(word)
    case word if originCountry.contains(word) => List(word)
    case word if car_price_syn.contains(word) => findTokenAfter(sentence, word).toList
    case word if car_engine_size_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_horsepower_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_acceleration_0_60mph_syn.contains(word) => findTokenAfter(sentence, word).toList
    case word if car_seating_capacity_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_cargo_capacity_syn.contains(word) => findTokenAfter(sentence, word).toList
    case word if car_safety_rating_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_infotainment_system_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_sunroof_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_gps_navigation_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_torque_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if number_of_doors_syn.contains(word) =>findTokenBefore(sentence, word).toList
    case word if number_of_cylinders_syn.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_top_speed.contains(word) => findTokenBefore(sentence, word).toList
    case word if car_weight.contains(word) => findTokenBefore(sentence, word).toList
    case word if Fuel_tank_capacity.contains(word) => findTokenBefore(sentence, word).toList
    case word if number_of_gears.contains(word) => findTokenBefore(sentence, word).toList
    case _ => Nil
  }
  intersection
}

def hasCommonElement[T](list1: List[T], list2: List[T]): Boolean = {
  list1.exists(list2.contains)
}


def handleUserInput(input: String): String = {
  val tokens = parseInput(input)
  println(tokens)
  tokens match {
    case Nil => "How can I help you?"
    case _ if hasCommonElement(tokens, recommendation_phrases_syn) => {

      val specs = specsexist(tokens, input)
      if (specs.isEmpty){
        val randomIndex = Random.nextInt(list.length)
        val randomElement = list(randomIndex)
        s"there is no recomendation tell me more about your prefrance like $randomElement "
      }
      else {
        storeUserPreferences(specs, input)
        "would you like to view the cars or you can tell me more about your preference "
      }
    }


    case _ if hasCommonElement(tokens, preference_phrases_syn) => {
      val specs = specsexist(tokens, input)
      println(specs)
      if (specs.isEmpty) {
        val randomIndex = Random.nextInt(list.length)
        val randomElement = list(randomIndex)
        s"there is no recomendation tell me more about your prefrance like $randomElement "
      }
      else {
        storeUserPreferences(specs, input)
        "would you like to view the cars or you can tell me more about your preference "
      }

    }
    case _ if hasCommonElement(tokens, negation_refusal_phrases_syn) => {

      "Delete preference, starting from the beginning."
    }
    case _ if hasCommonElement(tokens, car_dealership_syn) => {

      //view filtered cars with there dealership
      ""
    }
    case _ if hasCommonElement(tokens, acceptance_approval_phrases_syn) => {
      val inter = tokens.toSet.intersect(acceptance_approval_phrases_syn.toSet).toList

      if(inter.contains("view")){
        //view the cars
        ""
      }
      else{
        "what action should i take"
      }


    }

    case _ if hasCommonElement(tokens, gratitude_politeness_phrases_syn) => {
      "do you need further assistance? if not please enter exit."

    }
    case _ if hasCommonElement(tokens, allCars) => {
      val secps3 = specsexist(tokens, input)
      println (secps3)
      storeUserPreferences(secps3,input)
      "would you like to view the cars or you can tell me more about your preference "
    }
    case _ => "Error"
  }
}



@main def main(): Unit = {
  println(greetUser())
  var continue = true
  while (continue){
    val userInput = StdIn.readLine("Enter your query: ")
    if(userInput.toLowerCase == "exit"){
      continue = false
      println("Thank you for using the Car Recommendation System.")

    }
    println(handleUserInput(userInput))
  }
}