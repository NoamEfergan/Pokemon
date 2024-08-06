package networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable
data class Pokemon(
    val abilities: List<Ability>,

    @SerialName("base_experience") val baseExperience: Long,

    val cries: Cries,
    val forms: List<Species>,

    @SerialName("game_indices") val gameIndices: List<GameIndex>,

    val height: Long,

    @SerialName("held_items") val heldItems: JsonArray,

    val id: Long,

    @SerialName("is_default") val isDefault: Boolean,

    @SerialName("location_area_encounters") val locationAreaEncounters: String,

    val moves: List<Move>,
    val name: String,
    val order: Long,

    @SerialName("past_abilities") val pastAbilities: JsonArray,

    @SerialName("past_types") val pastTypes: JsonArray,

    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long
)

@Serializable
data class Ability(
    val ability: Species,

    @SerialName("is_hidden") val isHidden: Boolean,

    val slot: Long
)

@Serializable
data class Species(
    val name: String, val url: String
)

@Serializable
data class Cries(
    val latest: String, val legacy: String
)

@Serializable
data class GameIndex(
    @SerialName("game_index") val gameIndex: Long,

    val version: Species
)

@Serializable
data class Move(
    val move: Species,

    @SerialName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>
)

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at") val levelLearnedAt: Long,

    @SerialName("move_learn_method") val moveLearnMethod: Species,

    @SerialName("version_group") val versionGroup: Species
)

@Serializable
data class GenerationV(
    @SerialName("black-white") val blackWhite: Sprites
)

@Serializable
data class GenerationIv(
    @SerialName("diamond-pearl") val diamondPearl: Sprites,

    @SerialName("heartgold-soulsilver") val heartgoldSoulsilver: Sprites,

    val platinum: Sprites
)

@Serializable
data class Versions(
    @SerialName("generation-i") val generationI: GenerationI,

    @SerialName("generation-ii") val generationIi: GenerationIi,

    @SerialName("generation-iii") val generationIii: GenerationIii,

    @SerialName("generation-iv") val generationIv: GenerationIv,

    @SerialName("generation-v") val generationV: GenerationV,

    @SerialName("generation-vi") val generationVi: Map<String, Home>,

    @SerialName("generation-vii") val generationVii: GenerationVii,

    @SerialName("generation-viii") val generationViii: GenerationViii
)

@Serializable
data class Other(
    @SerialName("dream_world") val dreamWorld: DreamWorld,

    val home: Home,

    @SerialName("official-artwork") val officialArtwork: OfficialArtwork,

    val showdown: Sprites
)

@Serializable
data class GenerationI(
    @SerialName("red-blue") val redBlue: RedBlue,

    val yellow: RedBlue
)

@Serializable
data class RedBlue(
    @SerialName("back_default") val backDefault: String,

    @SerialName("back_gray") val backGray: String,

    @SerialName("back_transparent") val backTransparent: String,

    @SerialName("front_default") val frontDefault: String,

    @SerialName("front_gray") val frontGray: String,

    @SerialName("front_transparent") val frontTransparent: String
)

@Serializable
data class GenerationIi(
    val crystal: Crystal, val gold: Gold, val silver: Gold
)

@Serializable
data class Crystal(
    @SerialName("back_default") val backDefault: String,

    @SerialName("back_shiny") val backShiny: String,

    @SerialName("back_shiny_transparent") val backShinyTransparent: String,

    @SerialName("back_transparent") val backTransparent: String,

    @SerialName("front_default") val frontDefault: String,

    @SerialName("front_shiny") val frontShiny: String,

    @SerialName("front_shiny_transparent") val frontShinyTransparent: String,

    @SerialName("front_transparent") val frontTransparent: String
)

@Serializable
data class Gold(
    @SerialName("back_default") val backDefault: String,

    @SerialName("back_shiny") val backShiny: String,

    @SerialName("front_default") val frontDefault: String,

    @SerialName("front_shiny") val frontShiny: String,

    @SerialName("front_transparent") val frontTransparent: String? = null
)

@Serializable
data class GenerationIii(
    val emerald: OfficialArtwork,

    @SerialName("firered-leafgreen") val fireredLeafgreen: Gold,

    @SerialName("ruby-sapphire") val rubySapphire: Gold
)

@Serializable
data class OfficialArtwork(
    @SerialName("front_default") val frontDefault: String,

    @SerialName("front_shiny") val frontShiny: String
)

@Serializable
data class Home(
    @SerialName("front_default") val frontDefault: String,

    @SerialName("front_female") val frontFemale: JsonElement? = null,

    @SerialName("front_shiny") val frontShiny: String,

    @SerialName("front_shiny_female") val frontShinyFemale: JsonElement? = null
)

@Serializable
data class GenerationVii(
    val icons: DreamWorld,

    @SerialName("ultra-sun-ultra-moon") val ultraSunUltraMoon: Home
)

@Serializable
data class DreamWorld(
    @SerialName("front_default") val frontDefault: String,

    @SerialName("front_female") val frontFemale: JsonElement? = null
)

@Serializable
data class GenerationViii(
    val icons: DreamWorld
)

@Serializable
data class Stat(
    @SerialName("base_stat") val baseStat: Long,

    val effort: Long, val stat: Species
)

@Serializable
data class Type(
    val slot: Long, val type: Species
)