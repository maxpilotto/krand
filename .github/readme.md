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

You can pick a single value or a list of values from a List/Array/String by using the `Picker` class or the Kotlin extension methods, the latter don't have an option for the seed

```kotlin
val items = listOf("Dog", "Cat", "Goat", "Fox")
val weights = listOf(50, 50, 5, 10)
val picker = Picker("MySeed")

picker.one(items)                   // items.pickOne()    
picker.many(items, 10)              // items.pickMany(10)
picker.weighted(items, weights)     // items.weighted(weights)
picker.weighted(items, weights, 2)  // items.weighted(weights, 2)
```

## Shuffle

You can use the `Shuffler` utility to shuffle lists, arrays, strings or enum values

```kotlin
val values = listOf(1, 2, 3, 4)
val shuffle = Shuffler()

shuffle(values)    // 3, 4, 2, 1
```

```kotlin
val value = "hello1234"
val shuffle = Shuffler("MySeed")

shuffle(value)    // e12ol3lh4
```

## Custom generator

You can create your own custom generator by extending the class `AbstractGenerator` and using any of the Chance JS generators

```kotlin
// Odd/Even number generator
class CustomGenerator(seed: Any? = null) : AbstractGenerator<Int>(seed) {
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
Aadhar
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
Decimal
Depth
Dice
Dollar
Domain
Email
Euro
EvenNumber
FacebookID
Firstname
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
MAC
Millisecond
Minute
Month
Name
Natural
Normal
Note
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
Template
Tempo
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