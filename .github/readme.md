# KRand

Kotlin random data generation library that uses [ChanceJS](https://chancejs.com/) internally

## Getting started

```gradle
// Root build.gradle

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```gradle
// Module's build.gradle

dependencies {
    implementation 'com.github.maxpilotto:krand:version'
}
```

## Usage

All generators available with ChanceJS are also available with KRand

You can check all generators and the full docs at [ChanceJS's homepage](https://chancejs.com/)

```kotlin
BoolGenerator().one(likelihood = 50)            // true
IntegerGenerator().one(0, 100)                  // 30
CreditCardNumberGenerator().one("Mastercard")   // 5128243899559746
StateGenerator().one(full = true)               // Nevada
IPV6Generator().one()                           // 78c6:5c6f:500a:4d73:b4f3:f85c:08fa:5574
CoinGenerator().one()                           // tails
D8Generator().one()                             // 8
DiceGenerator("8d3").one()                      // 3, 3, 2, 1, 1, 1, 3, 2        
GenderGenerator().one(arrayOf(                  // Male_FTM
    "Male_FTM", "Female_MTF", "Non-Binary"
))
```

## Seeding

All generators take an optional seed, that is provided in the constructor

```kotlin
IntegerGenerator("my-seed").one()   //2147483647

CoinGenerator("my-seed").one() == CoinGenerator("my-seed").one()    // True
```

## Pickers

You can pick a single value or a list of values from a List/Array by using the `Pick` class or the provided extension methods

```kotlin
val items = listOf("Dog", "Cat", "Goat", "Fox")
val weights = listOf(50, 50, 5, 10)

Pick.one(items)        // items.pickOne()  
Pick.many(items, 2)    // items.pickMany(2)
Pick.weighted(items, weights)         // items.weighted(weights)
Pick.weighted(items, weights, 2)      // items.weighted(weights, 2)
```

## Custom generator

You can create your own custom generator by extending the class BaseGenerator and specifying:

+ Return type of the generator
+ Name of the base ChanceJS generator
+ As many methods as you want but at least one (can be empty) is recommended

```kotlin
// Odd/Even number generator
class CustomGenerator(seed: Any? = null) : BaseGenerator<Int>("integer", seed) {
    fun string(isOdd: Boolean) = one(isOdd).toString()
    fun many(isOdd: Boolean, count: Int) = List(count) { one(isOdd) }
    
    fun one(isOdd: Boolean): Int {
        val number = gen(
            //Check ChanceJS documentation to see available parameters
            mapOf(
                "min" to 0,
                "max" to 100
            )
        )

        if (isOdd) {
            return if (n % 2 != 0) n else n - 1
        } else {
            return if (n % 2 == 0) n else n - 1
        }
    }
}
```

## Available generators

```
AMPM
Address
Age
Altitude
AndroidID
Animal
AppleToken
Areacode
Avatar
Birthday
BlackBerryPIN
Bool
CF
CPF
Character
City
Coin
Color
Company
Coordinates
Country
CreditCardExpiry
CreditCardExpiryMonth
CreditCardExpiryYear
CreditCardNumber
CreditCardType
Currency
CurrencyPair
D10
D100
D12
D20
D30
D4
D6
D8
Date
Depth
Dice
Dollar
Domain
Email
Euro
EvenNumber
FacebookID
Falsy
Firstname
Float
GUID
Gender
Geohash
GoogleAnalytics
Hammertime
Hash
Hashtag
Hour
IP
IPV6
Integer
KloutScore
Lastname
Latitude
Letter
Locale
Longitude
Millisecond
Minute
Month
Name
Natural
Normal
OddNumber
Paragraph
Phone
Postal
Postcode
Prefix
Prime
Profession
Province
RadioCallSign
SSN
Second
Sentence
State
Street
String
Suffix
Syllable
Timestamp
Timezone
TopLevelDomain
Twitter
URL
Weekday
WindowsPhone7ANID
Word
Year
ZipCode
```
