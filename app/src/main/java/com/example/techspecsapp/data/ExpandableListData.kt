package com.example.techspecsapp.data

object ExpandableListData {
    fun getData(productDetail: ProductDetail): Map<String, List<String>> {
        val map = mutableMapOf<String, List<String>>()
        if (productDetail.camera.frontCamera != null) {
            map["Front Camera"] = productDetail.camera.frontCamera.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.camera.backCamera != null) {
            map["Back Camera"] = productDetail.camera.backCamera.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.camera.frontCamera2 != null) {
            map["Front Camera II"] =
                productDetail.camera.frontCamera2.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.camera.backCamera2 != null) {
            map["Back Camera II"] =
                productDetail.camera.backCamera2.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.design != null) {
            map["Design"] = productDetail.design.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.display != null) {
            map["Display"] = productDetail.display.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.battery != null) {
            map["Battery"] = productDetail.inside.battery.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.ram != null) {
            map["RAM"] = productDetail.inside.ram.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.cellular != null) {
            map["Cellular"] = productDetail.inside.cellular.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.processor != null) {
            map["Processor"] = productDetail.inside.processor.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.sensors != null) {
            map["Sensors"] = productDetail.inside.sensors.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.software != null) {
            map["Software"] = productDetail.inside.software.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.storage != null) {
            map["Storage"] = productDetail.inside.storage.map { "${it.key} : ${it.value}" }
        }
        if (productDetail.inside.wireless != null) {
            map["Wireless"] = productDetail.inside.wireless.map { "${it.key} : ${it.value}" }
        }
        return map
    }
}