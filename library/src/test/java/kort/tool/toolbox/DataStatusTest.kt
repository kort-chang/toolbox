package kort.tool.toolbox

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created by Kort on 2019/10/21.
 */
internal class DataStatusTest {
    @Test
    internal fun `combine two data status`() {
        val loadingDataStatus = DataStatus.Loading<String>()
        val success1DataStatus = DataStatus.Success("success")
        val success2DataStatus = DataStatus.Success("2")
        val combinedStatus = success1DataStatus.combine(success2DataStatus) { first, second ->
            first + second
        }
        print(combinedStatus)
        (combinedStatus as DataStatus.Success).apply {
            assertEquals(result,"success2")
        }
    }
}