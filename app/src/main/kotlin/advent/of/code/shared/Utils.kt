package advent.of.code.shared

fun getFilePath(fileName: String) =
    ClassLoader.getSystemClassLoader().getResource(fileName)?.path ?: error("Error reading resource")
