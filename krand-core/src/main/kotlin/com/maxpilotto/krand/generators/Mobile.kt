package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("android_id", String::class)
internal interface _AndroidIDGenerator

@Generator("apple_token", String::class)
internal interface _AppleTokenGenerator

@Generator("bb_pin", String::class)
internal interface _BlackBerryPINGenerator

@Generator("wp7_anid", String::class)
internal interface _WindowsPhone7ANIDGenerator

//@Generator("wp8_anid2", String::class)
//internal interface _WindowsPhone8ANID2Generator