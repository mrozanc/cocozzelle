package fr.rozanc.kokozzelle.dataview.transformer

import kotlin.reflect.KClass

interface ValueTransformer<in I, O : Any> {

    fun transformValue(value: I?, clazz: Class<O>): O?

    fun transformValue(value: I?, kClass: KClass<O>) = transformValue(value, kClass.java)
}
