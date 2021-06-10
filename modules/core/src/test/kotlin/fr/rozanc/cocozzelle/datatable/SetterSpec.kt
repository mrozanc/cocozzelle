package fr.rozanc.cocozzelle.datatable

import io.cucumber.datatable.DataTable
import io.kotest.core.spec.style.DescribeSpec
import java.lang.reflect.Type
import java.time.LocalDate

class SetterSpec : DescribeSpec({

    xdescribe("a setter") {
        data class MyModel(
            val aaa: Int,
            val bbb: LocalDate,
            val ccc: String,
            val ddd: Map<String, String>
        )

        class MyDataTableConverter : DataTable.TableConverter {
            override fun <T : Any> convert(dataTable: DataTable, type: Type): T {
                return convert(dataTable, type, false)
            }

            override fun <T : Any> convert(dataTable: DataTable, type: Type, transposed: Boolean): T {
                TODO("Not yet implemented")
            }

            override fun <T : Any> toList(dataTable: DataTable, itemType: Type): MutableList<T> {
                TODO("Not yet implemented")
            }

            override fun <T : Any> toLists(dataTable: DataTable, itemType: Type): MutableList<MutableList<T>> {
                TODO("Not yet implemented")
            }

            override fun <K : Any?, V : Any?> toMap(
                dataTable: DataTable?,
                keyType: Type?,
                valueType: Type?
            ): MutableMap<K, V> {
                TODO("Not yet implemented")
            }

            override fun <K : Any?, V : Any?> toMaps(
                dataTable: DataTable?,
                keyType: Type?,
                valueType: Type?
            ): MutableList<MutableMap<K, V>> {
                TODO("Not yet implemented")
            }

        }

        val dataTable = DataTable.create(
            listOf(
                listOf("aaa", "bbb", "ccc", "ddd['toto']"),
                listOf("1", "2021-06-07", " sample ", "hello")
            )
        )

        val values = dataTable.asList<MyModel>(MyModel::class.java)

    }
})