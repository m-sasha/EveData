/**
 * Encapsulates how the mutaplasmid can modify a single attribute.
 */
data class AttributeMutation(
    val attributeId: Int,
    val min: Double,
    val max: Double,
    val highIsGood: Boolean? = null
)


/**
 * Encapsulates a single mutaplasmid.
 */
data class Mutaplasmid(
    val id: Int,
    val targetTypeIds: List<Int>,
    val mutations: List<AttributeMutation>
)


/**
 * The list of existing mutaplasmids.
 */
val Mutaplasmids = listOf(
    Mutaplasmid(
        id = 47297,
        targetTypeIds = listOf(5975, 12052, 12076, 14118, 14120, 15751, 15764, 19315, 19321, 19327, 19339, 19345, 19351, 21478, 35659, 35660),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47299,
        targetTypeIds = listOf(5945, 12054, 12084, 14114, 14116, 14492, 14494, 14496, 14498, 14508, 14510, 14512, 14514, 15755, 15768, 19317, 19323, 19329, 19335, 19341, 19347, 19353, 19359, 21480, 35661, 35662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9700,
                max = 1.0350,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.9000,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47699,
        targetTypeIds = listOf(28514, 41038, 14652, 14650, 526, 527, 17500, 14648, 14654, 14262, 17559, 14264, 4025, 14266, 4027, 14268, 14270),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.4000,
                max = 1.8000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9700,
                max = 1.0350,
                highIsGood = false,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9500,
                max = 1.0750,
            ),
        )
    ),
    Mutaplasmid(
        id = 47700,
        targetTypeIds = listOf(28514, 15419, 41038, 14652, 14650, 526, 527, 17500, 14648, 14654, 14262, 17559, 14264, 4025, 14266, 4027, 14268, 14270),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.9000,
                max = 2.5000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9000,
                max = 1.1000,
                highIsGood = false,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47701,
        targetTypeIds = listOf(28514, 15419, 41038, 14652, 14650, 526, 527, 17500, 14648, 14654, 14262, 17559, 14264, 4025, 14266, 4027, 14268, 14270),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.0000,
                max = 2.0000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.0700,
                highIsGood = false,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47729,
        targetTypeIds = listOf(447, 5439, 5441, 5443, 5445, 448, 14256, 14258, 14260, 14252, 14254, 41061, 28518, 15433, 21512, 15887, 15893),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.4000,
                max = 1.8000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9500,
                max = 1.0750,
            ),
        )
    ),
    Mutaplasmid(
        id = 47730,
        targetTypeIds = listOf(447, 5439, 5441, 5443, 5445, 448, 14256, 14258, 14260, 14252, 14254, 41061, 28518, 15433, 21512, 15887, 15893),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.9000,
                max = 2.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47731,
        targetTypeIds = listOf(447, 5439, 5441, 5443, 5445, 448, 14256, 14258, 14260, 14252, 14254, 41061, 28518, 15433, 21512, 15887, 15893),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.0000,
                max = 2.0000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47733,
        targetTypeIds = listOf(3242, 5403, 5399, 21510, 5405, 3244, 41062, 15889, 15891, 5401, 14242, 14244, 14246, 14248, 15431, 14250, 28516),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.4000,
                max = 1.8000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9500,
                max = 1.0750,
            ),
        )
    ),
    Mutaplasmid(
        id = 47734,
        targetTypeIds = listOf(3242, 5403, 5399, 21510, 5405, 3244, 41062, 15889, 15891, 5401, 14242, 14244, 14246, 14248, 15431, 14250, 28516),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.9000,
                max = 2.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47735,
        targetTypeIds = listOf(3242, 5403, 5399, 21510, 5405, 3244, 41062, 15889, 15891, 5401, 14242, 14244, 14246, 14248, 15431, 14250, 28516),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.0000,
                max = 2.0000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47737,
        targetTypeIds = listOf(434, 440, 5971, 5973, 14122, 14124, 15747, 15759, 19313, 19319, 19325, 19337, 19343, 19349, 21476, 35658),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9700,
                max = 1.0350,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.9000,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47738,
        targetTypeIds = listOf(434, 440, 5971, 5973, 14122, 14124, 15747, 15759, 19313, 19319, 19325, 19337, 19343, 19349, 21476, 35658),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47739,
        targetTypeIds = listOf(434, 440, 5971, 5973, 14122, 14124, 15747, 15759, 19313, 19319, 19325, 19337, 19343, 19349, 21476, 35658),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.0700,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.8000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47741,
        targetTypeIds = listOf(5975, 12052, 12076, 14118, 14120, 15751, 15764, 19315, 19321, 19327, 19339, 19345, 19351, 21478, 35659, 35660),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.0700,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.8000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47742,
        targetTypeIds = listOf(5975, 12052, 12076, 14118, 14120, 15751, 15764, 19315, 19321, 19327, 19339, 19345, 19351, 21478, 35659, 35660),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9700,
                max = 1.0350,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.9000,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47743,
        targetTypeIds = listOf(5945, 12054, 12084, 14114, 14116, 14492, 14494, 14496, 14498, 14508, 14510, 14512, 14514, 15755, 15768, 19317, 19323, 19329, 19335, 19341, 19347, 19353, 19359, 21480, 35661, 35662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47744,
        targetTypeIds = listOf(5945, 12054, 12084, 14114, 14116, 14492, 14494, 14496, 14498, 14508, 14510, 14512, 14514, 15755, 15768, 19317, 19323, 19329, 19335, 19341, 19347, 19353, 19359, 21480, 35661, 35662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.0700,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.8000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47746,
        targetTypeIds = listOf(439, 438, 21470, 21857, 6003, 6001, 18692, 18686, 18680, 14110, 15761, 18670, 18664, 18658, 15749, 14112),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47747,
        targetTypeIds = listOf(439, 438, 21470, 21857, 6003, 6001, 18692, 18686, 18680, 14110, 15761, 18670, 18664, 18658, 15749, 14112),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47748,
        targetTypeIds = listOf(439, 438, 21470, 21857, 6003, 6001, 18692, 18686, 18680, 14110, 15761, 18670, 18664, 18658, 15749, 14112),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47750,
        targetTypeIds = listOf(6005, 12056, 12058, 14106, 14108, 15753, 15766, 18660, 18666, 18672, 18682, 18688, 18694, 21472, 35656),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47751,
        targetTypeIds = listOf(6005, 12056, 12058, 14106, 14108, 15753, 15766, 18660, 18666, 18672, 18682, 18688, 18694, 21472, 35656),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47752,
        targetTypeIds = listOf(6005, 12056, 12058, 14106, 14108, 15753, 15766, 18660, 18666, 18672, 18682, 18688, 18694, 21472, 35656),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47754,
        targetTypeIds = listOf(5955, 12066, 12068, 14102, 14104, 14484, 14486, 14488, 14490, 14500, 14502, 14504, 14506, 15757, 15770, 18662, 18668, 18674, 18676, 18684, 18690, 18696, 18698, 19491, 21474, 35657),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47755,
        targetTypeIds = listOf(5955, 12066, 12068, 14102, 14104, 14484, 14486, 14488, 14490, 14500, 14502, 14504, 14506, 15757, 15770, 18662, 18668, 18674, 18676, 18684, 18690, 18696, 18698, 19491, 21474, 35657),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47756,
        targetTypeIds = listOf(5955, 12066, 12068, 14102, 14104, 14484, 14486, 14488, 14490, 14500, 14502, 14504, 14506, 15757, 15770, 18662, 18668, 18674, 18676, 18684, 18690, 18696, 18698, 19491, 21474, 35657),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47766,
        targetTypeIds = listOf(23795, 22887, 15741, 19009, 19007, 19005, 19015, 19013, 19011, 19003, 19001, 18999, 13964, 13962, 15744, 17548, 28556, 17494, 14069, 523, 1183, 13963, 4529, 4531, 4535, 4533),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47767,
        targetTypeIds = listOf(23795, 22887, 15741, 19009, 19007, 19005, 19015, 19013, 19011, 19003, 19001, 18999, 13964, 13962, 15744, 17548, 28556, 17494, 14069, 523, 1183, 13963, 4529, 4533),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47768,
        targetTypeIds = listOf(23795, 22887, 15741, 19009, 19007, 19005, 19015, 19013, 19011, 19003, 19001, 18999, 13964, 13962, 15744, 17548, 28556, 17494, 14069, 523, 1183, 13963, 4529, 4533),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47770,
        targetTypeIds = listOf(3528, 3530, 13958, 13959, 13960, 14068, 15742, 15745, 17493, 17547, 19017, 19019, 19021, 19023, 19025, 19027, 19029, 19031, 19033, 22889, 23797, 28549, 4569, 4571, 4575, 4573),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47771,
        targetTypeIds = listOf(3528, 3530, 13958, 13959, 13960, 14068, 15742, 15745, 17493, 17547, 19017, 19019, 19021, 19023, 19025, 19027, 19029, 19031, 19033, 22889, 23797, 28549, 4569, 4573),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47772,
        targetTypeIds = listOf(3528, 3530, 13958, 13959, 13960, 14068, 15742, 15745, 17493, 17547, 19017, 19019, 19021, 19023, 19025, 19027, 19029, 19031, 19033, 22889, 23797, 28549, 4569, 4573),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47774,
        targetTypeIds = listOf(3538, 3540, 4609, 4611, 4613, 4615, 4621, 13955, 13956, 13957, 14067, 14552, 14554, 14848, 14849, 14850, 14851, 14852, 14853, 14854, 14855, 15160, 15161, 15162, 15163, 15743, 15746, 17492, 17546, 19035, 19036, 19037, 19038, 19039, 19040, 19041, 19042, 19043, 19044, 19045, 19046, 22891, 23799, 28544),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47775,
        targetTypeIds = listOf(3538, 3540, 4609, 4611, 4613, 4615, 4621, 13955, 13956, 13957, 14067, 14552, 14554, 14848, 14849, 14850, 14851, 14852, 14853, 14854, 14855, 15160, 15161, 15162, 15163, 15743, 15746, 17492, 17546, 19035, 19036, 19037, 19038, 19039, 19040, 19041, 19042, 19043, 19044, 19045, 19046, 22891, 23799, 28544),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47776,
        targetTypeIds = listOf(3538, 3540, 4609, 4611, 4613, 4615, 4621, 13955, 13956, 13957, 14067, 14552, 14554, 14848, 14849, 14850, 14851, 14852, 14853, 14854, 14855, 15160, 15161, 15162, 15163, 15743, 15746, 17492, 17546, 19035, 19036, 19037, 19038, 19039, 19040, 19041, 19042, 19043, 19044, 19045, 19046, 22891, 23799, 28544),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47778,
        targetTypeIds = listOf(399, 400, 6437, 6439, 6441, 6443, 13951, 13952, 15898, 15902, 19169, 19171, 19173, 19175, 19177, 19179, 20617),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 47779,
        targetTypeIds = listOf(399, 400, 6437, 6439, 6441, 6443, 13951, 13952, 15898, 15902, 19169, 19171, 19173, 19175, 19177, 19179, 20617),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47780,
        targetTypeIds = listOf(399, 400, 6437, 6439, 6441, 6443, 13951, 13952, 15898, 15902, 19169, 19171, 19173, 19175, 19177, 19179, 20617),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47782,
        targetTypeIds = listOf(10836, 10850, 10866, 10868, 10870, 10872, 13949, 13950, 15899, 15903, 19181, 19183, 19185, 19187, 19189, 19191, 20619),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 47783,
        targetTypeIds = listOf(10836, 10850, 10866, 10868, 10870, 10872, 13949, 13950, 15899, 15903, 19181, 19183, 19185, 19187, 19189, 19191, 20619),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47784,
        targetTypeIds = listOf(10836, 10850, 10866, 10868, 10870, 10872, 13949, 13950, 15899, 15903, 19181, 19183, 19185, 19187, 19189, 19191, 20619),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47786,
        targetTypeIds = listOf(10838, 10858, 10874, 10876, 10878, 10880, 13947, 13948, 14597, 14599, 14700, 14701, 14702, 14703, 15900, 15904, 19193, 19194, 19199, 19200, 19201, 19203, 19205, 19207, 20621),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 47787,
        targetTypeIds = listOf(10838, 10858, 10874, 10876, 10878, 10880, 13947, 13948, 14597, 14599, 14700, 14701, 14702, 14703, 15900, 15904, 19193, 19194, 19199, 19200, 19201, 19203, 19205, 19207, 20621),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47788,
        targetTypeIds = listOf(10838, 10858, 10874, 10876, 10878, 10880, 13947, 13948, 14597, 14599, 14700, 14701, 14702, 14703, 15900, 15904, 19193, 19194, 19199, 19200, 19201, 19203, 19205, 19207, 20621),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47790,
        targetTypeIds = listOf(10882, 10884, 10886, 10888, 13953, 13954, 14601, 14603, 14704, 14705, 14706, 14707, 15897, 15901, 19195, 19196, 19197, 19198, 19202, 19204, 19206, 19208, 20623, 10840, 10842),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 47791,
        targetTypeIds = listOf(10882, 10884, 10886, 10888, 13953, 13954, 14601, 14603, 14704, 14705, 14706, 14707, 15897, 15901, 19195, 19196, 19197, 19198, 19202, 19204, 19206, 19208, 20623, 10840, 10842),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47792,
        targetTypeIds = listOf(10882, 10884, 10886, 10888, 13953, 13954, 14601, 14603, 14704, 14705, 14706, 14707, 15897, 15901, 19195, 19196, 19197, 19198, 19202, 19204, 19206, 19208, 20623, 10840, 10842),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 47797,
        targetTypeIds = listOf(31922, 31924, 20627, 20625, 8427, 8521, 377, 380, 8401, 8481, 28742),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.9000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47798,
        targetTypeIds = listOf(31922, 31924, 20627, 20625, 8427, 8521, 377, 380, 8401, 8481, 28742),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.7000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47799,
        targetTypeIds = listOf(31922, 31924, 20627, 20625, 8427, 8521, 377, 380, 8401, 8481, 28742),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.9000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.8000,
                max = 1.4000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47801,
        targetTypeIds = listOf(31926, 16144, 20629, 8433, 8517, 3829, 3831, 8397, 8477, 31928, 28746, 19489),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.9000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47802,
        targetTypeIds = listOf(31926, 16144, 20629, 8433, 8517, 3829, 3831, 8397, 8477, 31928, 28746, 19489),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.7000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47803,
        targetTypeIds = listOf(31926, 16144, 20629, 8433, 8517, 3829, 3831, 8397, 8477, 31928, 28746, 19489),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.9000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.8000,
                max = 1.4000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47805,
        targetTypeIds = listOf(31930, 16146, 20631, 8419, 8529, 3839, 3841, 8409, 8489, 31932, 28744),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.9000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47806,
        targetTypeIds = listOf(31930, 16146, 20631, 8419, 8529, 3839, 3841, 8409, 8489, 31932, 28744),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.7000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47807,
        targetTypeIds = listOf(31930, 16146, 20631, 8419, 8529, 3839, 3841, 8409, 8489, 31932, 28744),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 72,
                min = 0.9000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 983,
                min = 0.8000,
                max = 1.4000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47809,
        targetTypeIds = listOf(23783, 23791, 11343, 11345, 11339, 11341, 11293, 20345, 31898, 31896, 28778, 23787, 11351, 11353, 11347, 11349, 11295, 20347, 31906, 31904, 28782),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47810,
        targetTypeIds = listOf(23783, 23791, 11343, 11345, 11339, 11341, 11293, 20345, 31898, 31896, 28778, 23787, 11351, 11353, 11347, 11349, 11295, 20347, 31906, 31904, 28782),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47811,
        targetTypeIds = listOf(23783, 23791, 11343, 11345, 11339, 11341, 11293, 20345, 31898, 31896, 28778, 23787, 11351, 11353, 11347, 11349, 11295, 20347, 31906, 31904, 28782),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47813,
        targetTypeIds = listOf(23789, 11311, 11313, 11307, 11309, 11297, 20349, 31910, 31908, 28784, 23793, 11319, 11321, 11315, 11317, 11299, 20351, 31918, 31916, 28786),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47814,
        targetTypeIds = listOf(23789, 11311, 11313, 11307, 11309, 11297, 20349, 31910, 31908, 28784, 23793, 11319, 11321, 11315, 11317, 11299, 20351, 31918, 31916, 28786),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47815,
        targetTypeIds = listOf(23789, 11311, 11313, 11307, 11309, 11297, 20349, 31910, 31908, 28784, 23793, 11319, 11321, 11315, 11317, 11299, 20351, 31918, 31916, 28786),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47816,
        targetTypeIds = listOf(23785, 11327, 11329, 11323, 11325, 11279, 20353, 31902, 31900, 28780),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47818,
        targetTypeIds = listOf(23785, 11327, 11329, 11323, 11325, 11279, 20353, 31902, 31900, 28780),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47819,
        targetTypeIds = listOf(23785, 11327, 11329, 11323, 11325, 11279, 20353, 31902, 31900, 28780),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 796,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 1159,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47821,
        targetTypeIds = listOf(533, 4471, 4473, 4475, 4477, 13003, 14160, 14162, 15794, 15800, 23815, 37622, 37623, 37624),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47822,
        targetTypeIds = listOf(533, 4471, 4473, 4475, 4477, 13003, 14160, 14162, 15794, 15800, 23815, 37622, 37623, 37624),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47823,
        targetTypeIds = listOf(533, 4471, 4473, 4475, 4477, 13003, 14160, 14162, 15794, 15800, 23815, 37622, 37623, 37624),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47825,
        targetTypeIds = listOf(12265, 12267, 14164, 14166, 15796, 15802, 16465, 16467, 16469, 16471, 23817, 37625, 37626, 37627),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47826,
        targetTypeIds = listOf(12265, 12267, 14164, 14166, 15796, 15802, 16465, 16467, 16469, 16471, 23817, 37625, 37626, 37627),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47827,
        targetTypeIds = listOf(12265, 12267, 14164, 14166, 15796, 15802, 16465, 16467, 16469, 16471, 23817, 37625, 37626, 37627),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47829,
        targetTypeIds = listOf(12269, 12271, 14168, 14170, 14832, 14834, 14836, 14838, 14840, 14842, 14844, 14846, 15798, 15804, 16473, 16475, 16477, 16479, 23819, 37628, 37629, 37630, 37631),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47830,
        targetTypeIds = listOf(12269, 12271, 14168, 14170, 14832, 14834, 14836, 14838, 14840, 14842, 14844, 14846, 15798, 15804, 16473, 16475, 16477, 16479, 23819, 37628, 37629, 37630, 37631),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47831,
        targetTypeIds = listOf(12269, 12271, 14168, 14170, 14832, 14834, 14836, 14838, 14840, 14842, 14844, 14846, 15798, 15804, 16473, 16475, 16477, 16479, 23819, 37628, 37629, 37630, 37631),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47835,
        targetTypeIds = listOf(32772),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47837,
        targetTypeIds = listOf(4391),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47839,
        targetTypeIds = listOf(32780),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47841,
        targetTypeIds = listOf(33076),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47843,
        targetTypeIds = listOf(33101),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 47845,
        targetTypeIds = listOf(33103),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48416,
        targetTypeIds = listOf(530, 5137, 5141, 13001, 14148, 14150, 15875, 15881, 19101, 19103, 19105, 23821),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48417,
        targetTypeIds = listOf(530, 5137, 5141, 13001, 14148, 14150, 15875, 15881, 19101, 19103, 19105, 23821),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48418,
        targetTypeIds = listOf(530, 5137, 5141, 13001, 14148, 14150, 15875, 15881, 19101, 19103, 19105, 23821),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48420,
        targetTypeIds = listOf(12257, 12259, 14156, 14158, 15877, 15883, 16505, 16507, 19107, 19109, 19111, 23824),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48421,
        targetTypeIds = listOf(12257, 12259, 14156, 14158, 15877, 15883, 16505, 16507, 19107, 19109, 19111, 23824),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48422,
        targetTypeIds = listOf(12257, 12259, 14156, 14158, 15877, 15883, 16505, 16507, 19107, 19109, 19111, 23824),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48424,
        targetTypeIds = listOf(12261, 12263, 14152, 14154, 14816, 14818, 14820, 14822, 14824, 14826, 14828, 14830, 15879, 15885, 16497, 16499, 19113, 19115, 19117, 19119, 23829),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48425,
        targetTypeIds = listOf(12261, 12263, 14152, 14154, 14816, 14818, 14820, 14822, 14824, 14826, 14828, 14830, 15879, 15885, 16497, 16499, 19113, 19115, 19117, 19119, 23829),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48426,
        targetTypeIds = listOf(12261, 12263, 14152, 14154, 14816, 14818, 14820, 14822, 14824, 14826, 14828, 14830, 15879, 15885, 16497, 16499, 19113, 19115, 19117, 19119, 23829),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48428,
        targetTypeIds = listOf(1185, 3488, 4787, 23801, 41212, 41213, 41214),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48429,
        targetTypeIds = listOf(1185, 3488, 4787, 23801, 41212, 41213, 41214),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.9000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48430,
        targetTypeIds = listOf(1185, 3488, 4787, 23801, 41212, 41213, 41214),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48432,
        targetTypeIds = listOf(2018, 3496, 6073, 23803, 41215, 41216, 41217),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48433,
        targetTypeIds = listOf(2018, 3496, 6073, 23803, 41215, 41216, 41217),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.9000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48434,
        targetTypeIds = listOf(2018, 3496, 6073, 23803, 41215, 41216, 41217),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48436,
        targetTypeIds = listOf(2020, 3504, 4871, 23805, 41218, 41219, 41220),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48437,
        targetTypeIds = listOf(2020, 3504, 4871, 23805, 41218, 41219, 41220),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.9000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 48438,
        targetTypeIds = listOf(2020, 3504, 4871, 23805, 41218, 41219, 41220),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 67,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 2267,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 49723,
        targetTypeIds = listOf(9944, 10188, 10190, 11101, 11103, 11105, 11107, 11109, 11111, 11113, 11115, 13945, 15144, 15146, 15148, 15150, 15416, 15895, 22917, 22919, 44113, 44114),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9950,
                max = 1.0080,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9850,
                max = 1.0100,
            ),
        )
    ),
    Mutaplasmid(
        id = 49724,
        targetTypeIds = listOf(9944, 10188, 10190, 11101, 11103, 11105, 11107, 11109, 11111, 11113, 11115, 13945, 15144, 15146, 15148, 15150, 15416, 15895, 22917, 22919, 44113, 44114),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9890,
                max = 1.0140,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9800,
                max = 1.0150,
            ),
        )
    ),
    Mutaplasmid(
        id = 49725,
        targetTypeIds = listOf(9944, 10188, 10190, 11101, 11103, 11105, 11107, 11109, 11111, 11113, 11115, 13945, 15144, 15146, 15148, 15150, 15416, 15895, 22917, 22919, 44113, 44114),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9800,
                max = 1.0200,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9750,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 49727,
        targetTypeIds = listOf(1893, 2363, 2364, 5845, 5846, 5849, 5854, 5855, 5856, 5857, 5858, 13941, 13943, 14800, 14802, 14804, 14806, 14808, 14810, 14812, 14814, 15397, 15435, 15808, 15810, 23900, 23902, 44111),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9950,
                max = 1.0080,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9850,
                max = 1.0100,
            ),
        )
    ),
    Mutaplasmid(
        id = 49728,
        targetTypeIds = listOf(1893, 2363, 2364, 5845, 5846, 5849, 5854, 5855, 5856, 5857, 5858, 13941, 13943, 14800, 14802, 14804, 14806, 14808, 14810, 14812, 14814, 15397, 15435, 15808, 15810, 23900, 23902, 44111),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9890,
                max = 1.0140,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9800,
                max = 1.0150,
            ),
        )
    ),
    Mutaplasmid(
        id = 49729,
        targetTypeIds = listOf(1893, 2363, 2364, 5845, 5846, 5849, 5854, 5855, 5856, 5857, 5858, 13941, 13943, 14800, 14802, 14804, 14806, 14808, 14810, 14812, 14814, 15397, 15435, 15808, 15810, 23900, 23902, 44111),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9800,
                max = 1.0200,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9750,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 49731,
        targetTypeIds = listOf(518, 519, 520, 5913, 5915, 5917, 5919, 5929, 5931, 5933, 5935, 13939, 14536, 14538, 14540, 14542, 15447, 15806, 21486, 21488, 44112),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9950,
                max = 1.0080,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9850,
                max = 1.0100,
            ),
        )
    ),
    Mutaplasmid(
        id = 49732,
        targetTypeIds = listOf(518, 519, 520, 5913, 5915, 5917, 5919, 5929, 5931, 5933, 5935, 13939, 14536, 14538, 14540, 14542, 15447, 15806, 21486, 21488, 44112),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9890,
                max = 1.0140,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9800,
                max = 1.0150,
            ),
        )
    ),
    Mutaplasmid(
        id = 49733,
        targetTypeIds = listOf(518, 519, 520, 5913, 5915, 5917, 5919, 5929, 5931, 5933, 5935, 13939, 14536, 14538, 14540, 14542, 15447, 15806, 21486, 21488, 44112),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9800,
                max = 1.0200,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9750,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 49735,
        targetTypeIds = listOf(47908, 47909, 47911, 52244, 52242),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9950,
                max = 1.0080,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9850,
                max = 1.0100,
            ),
        )
    ),
    Mutaplasmid(
        id = 49736,
        targetTypeIds = listOf(47908, 47909, 47911, 52242, 52244),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9890,
                max = 1.0140,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9800,
                max = 1.0150,
            ),
        )
    ),
    Mutaplasmid(
        id = 49737,
        targetTypeIds = listOf(47908, 47909, 47911, 52242, 52244),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.9800,
                max = 1.0200,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9750,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 49739,
        targetTypeIds = listOf(12274, 13935, 13937, 14528, 14530, 14532, 14534, 14684, 14686, 14688, 14690, 15681, 15683, 16457, 16459, 16461, 16463, 21482, 21484, 22291, 28563, 46270, 47447, 47448),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9850,
                max = 1.0100,
            ),
            AttributeMutation(
                attributeId = 213,
                min = 0.9950,
                max = 1.0080,
            ),
        )
    ),
    Mutaplasmid(
        id = 49740,
        targetTypeIds = listOf(12274, 13935, 13937, 14528, 14530, 14532, 14534, 14684, 14686, 14688, 14690, 15681, 15683, 16457, 16459, 16461, 16463, 21482, 21484, 22291, 28563, 46270, 47447, 47448),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9800,
                max = 1.0150,
            ),
            AttributeMutation(
                attributeId = 213,
                min = 0.9890,
                max = 1.0140,
            ),
        )
    ),
    Mutaplasmid(
        id = 49741,
        targetTypeIds = listOf(12274, 13935, 13937, 14528, 14530, 14532, 14534, 14684, 14686, 14688, 14690, 15681, 15683, 16457, 16459, 16461, 16463, 21482, 21484, 22291, 28563, 46270, 47447, 47448),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 204,
                min = 0.9750,
                max = 1.0250,
            ),
            AttributeMutation(
                attributeId = 213,
                min = 0.9800,
                max = 1.0200,
            ),
        )
    ),
    Mutaplasmid(
        id = 52224,
        targetTypeIds = listOf(2048, 521, 5839, 41210, 41200, 41201, 41202, 41203, 41204, 41205, 41206, 41208, 23418, 41211, 2046, 41207),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 974,
                min = 0.9000,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 975,
                min = 0.9000,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 976,
                min = 0.9000,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 977,
                min = 0.9000,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 52225,
        targetTypeIds = listOf(2048, 521, 5839, 41210, 41200, 41201, 41202, 41203, 41204, 41205, 41206, 41208, 23418, 41211, 2046, 41207),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 974,
                min = 0.8000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 975,
                min = 0.8000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 976,
                min = 0.8000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 977,
                min = 0.8000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 52226,
        targetTypeIds = listOf(2048, 521, 5839, 41210, 41200, 41201, 41202, 41203, 41204, 41205, 41206, 41208, 23418, 41211, 2046, 41207),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 974,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 975,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 976,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 977,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 52228,
        targetTypeIds = listOf(47254, 47255, 47256, 47257, 47258),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0750,
                highIsGood = true,
            ),
            AttributeMutation(
                attributeId = 974,
                min = 0.9000,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 975,
                min = 0.9000,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 976,
                min = 0.9000,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 977,
                min = 0.9000,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 52229,
        targetTypeIds = listOf(47254, 47255, 47256, 47257, 47258),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1500,
                highIsGood = true,
            ),
            AttributeMutation(
                attributeId = 974,
                min = 0.8000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 975,
                min = 0.8000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 976,
                min = 0.8000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 977,
                min = 0.8000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 52231,
        targetTypeIds = listOf(47254, 47255, 47256, 47257, 47258),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
                highIsGood = true,
            ),
            AttributeMutation(
                attributeId = 974,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 975,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 976,
                min = 0.7000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 977,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56269,
        targetTypeIds = listOf(40750, 40756, 40752, 40754, 40758, 40764, 40762, 14664, 14666, 14668, 14670),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.4000,
                max = 1.8000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9500,
                max = 1.0750,
            ),
        )
    ),
    Mutaplasmid(
        id = 56270,
        targetTypeIds = listOf(40750, 40756, 40752, 40754, 40758, 40764, 40762, 14664, 14666, 14668, 14670),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.9000,
                max = 2.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56271,
        targetTypeIds = listOf(40750, 40756, 40752, 40754, 40758, 40764, 40762, 14664, 14666, 14668, 14670),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.0000,
                max = 2.0000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 56272,
        targetTypeIds = listOf(40730, 40733, 40731, 40732, 40734, 40737, 40736, 14656, 14658, 14660, 14662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.9000,
                max = 2.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56273,
        targetTypeIds = listOf(40730, 40733, 40731, 40732, 40734, 40737, 40736, 14656, 14658, 14660, 14662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.0000,
                max = 2.0000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 56274,
        targetTypeIds = listOf(40730, 40733, 40731, 40732, 40734, 40737, 40736, 14656, 14658, 14660, 14662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 1.4000,
                max = 1.8000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.9500,
                max = 1.0750,
            ),
        )
    ),
    Mutaplasmid(
        id = 56275,
        targetTypeIds = listOf(41236, 41238, 41237, 41239, 41240, 41241),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
        )
    ),
    Mutaplasmid(
        id = 56276,
        targetTypeIds = listOf(41236, 41238, 41237, 41239, 41240, 41241),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56277,
        targetTypeIds = listOf(41236, 41238, 41237, 41239, 41240, 41241),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56278,
        targetTypeIds = listOf(41249, 41252, 41251, 41250, 41253, 41254, 41255),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9700,
                max = 1.0350,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.9000,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 56279,
        targetTypeIds = listOf(41249, 41252, 41251, 41250, 41253, 41254, 41255),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56280,
        targetTypeIds = listOf(41249, 41252, 41251, 41250, 41253, 41254, 41255),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 20,
                min = 0.9500,
                max = 1.0700,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 554,
                min = 0.8000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56281,
        targetTypeIds = listOf(20701, 41499, 41498, 41500, 3534, 41501, 41502),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56282,
        targetTypeIds = listOf(20701, 41499, 41498, 41500, 3534, 41501, 41502),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56283,
        targetTypeIds = listOf(20701, 41499, 41498, 41500, 3534, 41501, 41502),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 56284,
        targetTypeIds = listOf(41503),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 84,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56285,
        targetTypeIds = listOf(20703, 41505, 41506, 41507, 3542, 41510, 41509, 41508),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9500,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9500,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 56286,
        targetTypeIds = listOf(20703, 41505, 41506, 41507, 3542, 41510, 41509, 41508),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9000,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56287,
        targetTypeIds = listOf(20703, 41505, 41506, 41507, 3542, 41510, 41509, 41508),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.9000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.9250,
                max = 1.0500,
            ),
        )
    ),
    Mutaplasmid(
        id = 56288,
        targetTypeIds = listOf(41504),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 68,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 73,
                min = 0.8000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 1795,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56289,
        targetTypeIds = listOf(40665, 40666, 40667, 40668, 40670, 40669),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56290,
        targetTypeIds = listOf(40665, 40666, 40667, 40668, 40670, 40669),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56291,
        targetTypeIds = listOf(40665, 40666, 40667, 40668, 40670, 40669),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 90,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56292,
        targetTypeIds = listOf(40659, 40660, 40661, 40664, 40663, 40662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8500,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9500,
                max = 1.1000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56293,
        targetTypeIds = listOf(40659, 40660, 40661, 40664, 40663, 40662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.6000,
                max = 1.4000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.7000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56294,
        targetTypeIds = listOf(40659, 40660, 40661, 40664, 40663, 40662),
        mutations = listOf(
            AttributeMutation(
                attributeId = 6,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.8000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 97,
                min = 0.9000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56299,
        targetTypeIds = listOf(20280, 4292),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.9500,
                max = 1.2500,
            ),
            AttributeMutation(
                attributeId = 2306,
                min = 0.9943,
                max = 1.0100,
            ),
            AttributeMutation(
                attributeId = 2307,
                min = 0.9950,
                max = 1.0080,
            ),
            AttributeMutation(
                attributeId = 2346,
                min = 1.0250,
                max = 0.9500,
            ),
            AttributeMutation(
                attributeId = 2347,
                min = 0.9500,
                max = 1.0250,
            ),
        )
    ),
    Mutaplasmid(
        id = 56300,
        targetTypeIds = listOf(20280, 4292),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 2306,
                min = 0.9733,
                max = 1.0267,
            ),
            AttributeMutation(
                attributeId = 2307,
                min = 0.9800,
                max = 1.0200,
            ),
            AttributeMutation(
                attributeId = 2346,
                min = 1.1000,
                max = 0.9000,
            ),
            AttributeMutation(
                attributeId = 2347,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 56301,
        targetTypeIds = listOf(20280, 4292),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.8500,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 2306,
                min = 0.9730,
                max = 1.0187,
            ),
            AttributeMutation(
                attributeId = 2307,
                min = 0.9800,
                max = 1.0140,
            ),
            AttributeMutation(
                attributeId = 2346,
                min = 1.0500,
                max = 0.9250,
            ),
            AttributeMutation(
                attributeId = 2347,
                min = 0.9000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60460,
        targetTypeIds = listOf(2203, 2205, 28264, 31864, 28262, 2454, 2456, 28276, 28274, 31880, 2464, 2466, 28280, 31872, 28278, 2486, 2488, 28304, 31888, 28302),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60461,
        targetTypeIds = listOf(2203, 2205, 28264, 31864, 28262, 2454, 2456, 28276, 28274, 31880, 2464, 2466, 28280, 31872, 28278, 2486, 2488, 28304, 31888, 28302),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 1.0000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60462,
        targetTypeIds = listOf(2203, 2205, 28264, 31864, 28262, 2454, 2456, 28276, 28274, 31880, 2464, 2466, 28280, 31872, 28278, 2486, 2488, 28304, 31888, 28302),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 1.0000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 60463,
        targetTypeIds = listOf(2444, 2446, 28288, 28286, 31884, 2193, 2195, 28292, 31870, 28290, 2476, 2478, 28268, 28266, 31892, 1201, 2436, 28308, 28306, 31876),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60464,
        targetTypeIds = listOf(2444, 2446, 28288, 28286, 31884, 2193, 2195, 28292, 31870, 28290, 2476, 2478, 28268, 28266, 31892, 1201, 2436, 28308, 28306, 31876),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 1.0000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60465,
        targetTypeIds = listOf(2444, 2446, 28288, 28286, 31884, 2193, 2195, 28292, 31870, 28290, 2476, 2478, 28268, 28266, 31892, 1201, 2436, 28308, 28306, 31876),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 1.0000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 60466,
        targetTypeIds = listOf(2444, 2446, 28288, 28286, 31884, 2193, 2195, 28292, 31870, 28290, 2476, 2478, 28268, 28266, 31892, 1201, 2436, 28308, 28306, 31876),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60467,
        targetTypeIds = listOf(23561, 28211, 31886, 23525, 28213, 31868, 23563, 28215, 31894, 23559, 28209, 31878),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60468,
        targetTypeIds = listOf(23561, 28211, 31886, 23525, 28213, 31868, 23563, 28215, 31894, 23559, 28209, 31878),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 1.0000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60469,
        targetTypeIds = listOf(23561, 28211, 31886, 23525, 28213, 31868, 23563, 28215, 31894, 23559, 28209, 31878),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 1.0000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 60470,
        targetTypeIds = listOf(23561, 28211, 31886, 23525, 28213, 31868, 23563, 28215, 31894, 23559, 28209, 31878),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60471,
        targetTypeIds = listOf(2203, 2205, 28264, 31864, 28262, 2454, 2456, 28276, 28274, 31880, 2464, 2466, 28280, 31872, 28278, 2486, 2488, 28304, 31888, 28302),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60472,
        targetTypeIds = listOf(2183, 2185, 28272, 28270, 31882, 2173, 2175, 28284, 28282, 31866, 15510, 21640, 28296, 28294, 31890, 15508, 21638, 28300, 28298, 31874),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60473,
        targetTypeIds = listOf(2183, 2185, 28272, 28270, 31882, 2173, 2175, 28284, 28282, 31866, 15510, 21640, 28296, 28294, 31890, 15508, 21638, 28300, 28298, 31874),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 1.0000,
                max = 1.2000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60474,
        targetTypeIds = listOf(2183, 2185, 28272, 28270, 31882, 2173, 2175, 28284, 28282, 31866, 15510, 21640, 28296, 28294, 31890, 15508, 21638, 28300, 28298, 31874),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 1.0000,
                max = 1.3000,
            ),
        )
    ),
    Mutaplasmid(
        id = 60475,
        targetTypeIds = listOf(2183, 2185, 28272, 28270, 31882, 2173, 2175, 28284, 28282, 31866, 15510, 21640, 28296, 28294, 31890, 15508, 21638, 28300, 28298, 31874),
        mutations = listOf(
            AttributeMutation(
                attributeId = 9,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 37,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 54,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 64,
                min = 0.7000,
                max = 1.1000,
            ),
            AttributeMutation(
                attributeId = 158,
                min = 1.0000,
                max = 1.3000,
            ),
            AttributeMutation(
                attributeId = 160,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 263,
                min = 0.7000,
                max = 1.1500,
            ),
            AttributeMutation(
                attributeId = 265,
                min = 0.7000,
                max = 1.1500,
            ),
        )
    ),
    Mutaplasmid(
        id = 60476,
        targetTypeIds = listOf(4393, 4405, 41034, 33846, 33842, 33844, 33848, 32919, 32921, 32923, 32925),
        mutations = listOf(
            AttributeMutation(
                attributeId = 50,
                min = 0.7000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 1255,
                min = 0.8000,
                max = 1.2000,
            ),
        )
    ),
    Mutaplasmid(
        id = 60477,
        targetTypeIds = listOf(24283, 41415, 41414, 41417, 32951, 32953, 32955, 32957),
        mutations = listOf(
            AttributeMutation(
                attributeId = 30,
                min = 0.7000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 50,
                min = 0.7000,
                max = 1.5000,
            ),
            AttributeMutation(
                attributeId = 2335,
                min = 0.9880,
                max = 1.0120,
            ),
            AttributeMutation(
                attributeId = 2336,
                min = 0.9880,
                max = 1.0120,
            ),
            AttributeMutation(
                attributeId = 2337,
                min = 0.9910,
                max = 1.1320,
            ),
            AttributeMutation(
                attributeId = 2338,
                min = 0.9880,
                max = 1.0120,
            ),
        )
    ),
)
