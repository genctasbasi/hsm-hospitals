package com.escmobile.hsm_hospitals.data

import android.os.Environment
import com.escmobile.hsm_hospitals.R
import com.escmobile.hsm_hospitals.data.model.Hospital
import com.escmobile.hsm_hospitals.data.model.SubType
import com.escmobile.hsm_hospitals.utils.Failure
import com.escmobile.hsm_hospitals.utils.Response
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import kotlin.random.Random

const val SEPARATOR_CHAR = 65533.toChar()

class DataLoaderRepositoryImpl : DataLoaderRepository {

    @Suppress("DEPRECATION")
    private val path =
        "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$DATA_FILE_NAME"

    override fun loadData(): Response<List<Hospital>> {

        if (!hasDataFile()) {
            return Response(Failure(message = "Data file doesn't exist"))
        }

        var stream: FileInputStream? = null
        var reader: InputStreamReader? = null

        return try {

            val hospitals = mutableListOf<Hospital>()
            stream = FileInputStream(getDataFile())
            reader = InputStreamReader(stream)

            val bufferedReader = BufferedReader(InputStreamReader(stream))

            bufferedReader.readLine()  // header line - we don't really need that
            var dataLine = bufferedReader.readLine()

            while (dataLine != null) {
                val data = dataLine.split(SEPARATOR_CHAR)
                hospitals.add(getHospital(data))

                dataLine = bufferedReader.readLine()    // next line
            }

            Response(data = hospitals)

        } catch (exception: Exception) {
            Response(Failure(message = exception.message ?: "Loading data file failed."))
        } finally {
            stream?.close()
            reader?.close()
        }
    }

    private fun getHospital(data: List<String>): Hospital {
        return Hospital(
            organisationID = data[0],
            organisationCode = data[1],
            organisationType = data[2],
            subType = SubType.values().firstOrNull { it.name == data[3] }
                ?: SubType.UNKNOWN,
            sector = data[4],
            organisationStatus = data[5],
            isPimsManaged = data[6],
            organisationName = data[7],
            address1 = data[8],
            address2 = data[9],
            address3 = data[10],
            city = data[11],
            county = data[12],
            postcode = data[13],
            latitude = data[14],
            longitude = data[15],
            parentODSCode = data[16],
            parentName = data[17],
            phone = data[18],
            email = data[19],
            website = data[20],
            fax = data[21],
            tintColour = colourList[Random.nextInt(0, colourList.size - 1)]
        )
    }

    override fun getDataFile() = File(path)
    override fun hasDataFile() = getDataFile().isFile

    private val colourList = listOf(
        R.color.hospitalColourTint1,
        R.color.hospitalColourTint2,
        R.color.hospitalColourTint3,
        R.color.hospitalColourTint4,
        R.color.hospitalColourTint5
    )
}