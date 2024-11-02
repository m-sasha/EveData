EveData is a Kotlin library for working with the Eve Online SDE (Static Data Export) and the information contained therein.

**Note**: EveData is made for and is part of [Theorycrafter](https://theorycrafter.pro/).
As such it focuses on the kinds of data needed for fitting ships. For example, you will find ship and module information
here, but not the constituents of asteroids.

There are two parts:

### Converting the SDE into `eve_data.dat`.
To work with the SDE in a sane way, we must first parse, fix and repackage it.
To do so run the `updateEveData` gradle task. This will download the SDE and build `eve_data.dat`. If you then
want to re-build `eve_data.dat` without re-downloading the SDE, run `generateEveData`.

The source code for these tasks is in `buildSrc` (but the tasks themselves are in the main `build.gradle.kts`).

#### Icons
The tasks that generate `eve_data.dat` also create or update the various Eve Online icons:
- Icons for item types, e.g. "Damage Control II". These are downloaded from https://images.evetech.net and put into
`src/main/resources/icons/items`.
- Icons for market categories, e.g. "Ship Equipment". These are extracted from the Eve Online game client itself, so you
need to have it installed and the path to it specified correctly in the main `build.gradle.kts`. Look for the `eveCacheDir`
property of the `generateEveData` task. You also need to have looked at the corresponding market category, as the client
downloads them on-demand.

If you don't care about icons, comment out the lines in the `importSde` function that update them (the last two lines).

### Reading and modeling the data
This reads `eve_data.dat` and creates Kotlin objects that you can work with. The entry
point to read the data is `EveData.loadStandard()` or `EveData.load(InputStream)`. For example:
```kotlin
fun main() {
    val eveData = EveData.loadStandard()
    val caracal = eveData.shipType("Caracal")
    println("The ${caracal.name} has ${caracal.fitting.slots.high} high slots")
}
```
should print
```
The Caracal has 5 high slots
```

Start at the `EveData` class and explore from there.

The source code for this part is in `src`.