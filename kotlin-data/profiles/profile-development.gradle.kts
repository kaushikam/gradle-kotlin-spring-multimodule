//val currentDir = project(":kotlin-data").projectDir
val getConfig: () -> String by extra
apply(from=getConfig())

val applicationProperties: HashMap<String, String> by extra

applicationProperties["datasourcePassword"] = "redhat"

tasks.withType<ProcessResources> {
    filesMatching("application.properties") {
        expand(applicationProperties)
    }
}