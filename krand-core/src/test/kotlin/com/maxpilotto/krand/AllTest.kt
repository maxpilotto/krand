package com.maxpilotto.krand

import com.maxpilotto.krand.generators.*
import com.maxpilotto.krand.generators.DiceGenerator
import com.maxpilotto.krand.generators.EvenNumberGenerator
import com.maxpilotto.krand.generators.OddNumberGenerator
import org.junit.jupiter.api.Test

class AllTest {
    @Test
    fun all() {
        // Auto generated
        println(FalsyGenerator().gen())
        println(AMPMGenerator().gen())
        println(AddressGenerator().gen())
        println(AgeGenerator().gen())
        println(AltitudeGenerator().gen())
        println(AndroidIDGenerator().gen())
        println(AnimalGenerator().gen())
        println(AppleTokenGenerator().gen())
        println(AreacodeGenerator().gen())
        println(AvatarGenerator().gen())
        println(BirthdayGenerator().gen())
        println(BlackBerryPINGenerator().gen())
        println(BoolGenerator().gen())
        println(CFGenerator().gen())
        println(CPFGenerator().gen())
        println(CharacterGenerator().gen())
        println(CityGenerator().gen())
        println(CoinGenerator().gen())
        println(ColorGenerator().gen())
        println(CompanyGenerator().gen())
        println(CoordinatesGenerator().gen())
        println(CountryGenerator().gen())
        println(CreditCardExpiryGenerator().gen())
        println(CreditCardExpiryMonthGenerator().gen())
        println(CreditCardExpiryYearGenerator().gen())
        println(CreditCardNumberGenerator().gen())
        println(CreditCardTypeGenerator().gen())
        println(CurrencyGenerator().gen())
        println(CurrencyPairGenerator().gen())
        println(D100Generator().gen())
        println(D10Generator().gen())
        println(D12Generator().gen())
        println(D20Generator().gen())
        println(D30Generator().gen())
        println(D4Generator().gen())
        println(D6Generator().gen())
        println(D8Generator().gen())
        println(DateGenerator().gen())
        println(DepthGenerator().gen())
        println(DollarGenerator().gen())
        println(DomainGenerator().gen())
        println(EmailGenerator().gen())
        println(EuroGenerator().gen())
        println(FacebookIDGenerator().gen())
        println(FirstnameGenerator().gen())
        println(FloatGenerator().gen())
        println(GUIDGenerator().gen())
        println(GenderGenerator().gen())
        println(GeohashGenerator().gen())
        println(GoogleAnalyticsGenerator().gen())
        println(HammertimeGenerator().gen())
        println(HashGenerator().gen())
        println(HashtagGenerator().gen())
        println(HourGenerator().gen())
        println(IPGenerator().gen())
        println(IPV6Generator().gen())
        println(IntegerGenerator().gen())
        println(KloutScoreGenerator().gen())
        println(LastnameGenerator().gen())
        println(LatitudeGenerator().gen())
        println(LetterGenerator().gen())
        println(LocaleGenerator().gen())
        println(LongitudeGenerator().gen())
        println(MillisecondGenerator().gen())
        println(MinuteGenerator().gen())
        println(MonthGenerator().gen())
        println(NameGenerator().gen())
        println(NaturalGenerator().gen())
        println(NormalGenerator().gen())
        println(ParagraphGenerator().gen())
        println(PhoneGenerator().gen())
        println(PostalGenerator().gen())
        println(PostcodeGenerator().gen())
        println(PrefixGenerator().gen())
        println(PrimeGenerator().gen())
        println(ProfessionGenerator().gen())
        println(ProvinceGenerator().gen())
        println(RadioCallSignGenerator().gen())
        println(SSNGenerator().gen())
        println(SecondGenerator().gen())
        println(SentenceGenerator().gen())
        println(StateGenerator().gen())
        println(StreetGenerator().gen())
        println(StringGenerator().gen())
        println(SuffixGenerator().gen())
        println(SyllableGenerator().gen())
        println(TimestampGenerator().gen())
        println(TimezoneGenerator().gen())
        println(TopLevelDomainGenerator().gen())
        println(TwitterGenerator().gen())
        println(URLGenerator().gen())
        println(WeekdayGenerator().gen())
        println(WindowsPhone7ANIDGenerator().gen())
//        println(WindowsPhone8ANID2Generator().gen())  // Doesn't work
        println(WordGenerator().gen())
        println(YearGenerator().gen())
        println(ZipCodeGenerator().gen())

        // Custom
        println(DiceGenerator("12d6").gen())
        println(EvenNumberGenerator().gen())
        println(OddNumberGenerator().gen())
    }
}