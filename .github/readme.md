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
    implementation 'com.github.maxpilotto:krand:$version'
}
```

## Usage

All generators available with ChanceJS are also available with KRand

All generators extend the base `AbstractGenerator` which has three methods available: `one`, `many` and `string`.

You can check all generators and the full docs at [ChanceJS's homepage](https://chancejs.com/)

```kotlin
BoolGenerator().likelihood(50).one()            // true
IntegerGenerator().min(0).max(100).one()        // 30
StateGenerator().full().one()                   // Nevada
IPV6Generator().one()                           // 78c6:5c6f:500a:4d73:b4f3:f85c:08fa:5574
CoinGenerator().one()                           // tails
DiceGenerator().rolls(4).max(4).one()           // 3, 3, 2, 1  

GenderGenerator().extraGenders(
    "Male (FTM)", 
    "Female (MTF)", 
    "Non Binary"
).one()                                         // Male (FTM)

CreditCardNumberGenerator()
    .type(CardType.Mastercard)
    .one()                                      // 5114582008438961
```

## Seeding

All generators can take an optional seed parameter

```kotlin
IntegerGenerator("my-seed").one()   //2147483647

CoinGenerator("my-seed").one() == CoinGenerator("my-seed").one()    // True
```

## Pickers

You can pick a single value or a list of values from a List/Array by using the `Pick` class or the Kotlin extension methods

```kotlin
val items = listOf("Dog", "Cat", "Goat", "Fox")
val weights = listOf(50, 50, 5, 10)

Pick.one(items)        // items.pickOne()  
Pick.many(items, 2)    // items.pickMany(2)
Pick.weighted(items, weights)         // items.weighted(weights)
Pick.weighted(items, weights, 2)      // items.weighted(weights, 2)
```

## Custom generator

You can create your own custom generator by extending the class `AbstractGenerator` and using any of the Chance JS generators

```kotlin
// Odd/Even number generator
class CustomGenerator : AbstractGenerator<Int>() {
    var isOdd: Boolean = false
        private set

    override fun one(): Int {
        val number = execute<Int>("integer")  // ChanceJS generator name

        return if (isOdd) {
            if (number % 2 != 0) number else number - 1
        } else {
            if (number % 2 == 0) number else number - 1
        }
    }

    fun isOdd(isOdd: Boolean) = apply {
        this.isOdd = isOdd
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
//Falsy     //Doesn't work
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
//WindowsPhone8ANID2    //Doesn't work
Word
Year
ZipCode
```
