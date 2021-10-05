/*
Simple utility class to pick up test message feed from assets
* */
package com.WarwickWestonWright.MM_Messenger.FileHandlers

import android.content.res.AssetManager
import java.io.InputStream

class TextAssetMan {
    interface ITextAssetMan { fun getText(text: String) }
    private val iTextAssetMan : ITextAssetMan
    private val am : AssetManager
    private lateinit var inputStream : InputStream
    private lateinit var textVal: String

    constructor(iTextAssetMan : ITextAssetMan, am : AssetManager) {
        this.iTextAssetMan = iTextAssetMan
        this.am = am
    }

    fun getTextAsset(assetFile : String) {
        Thread {
            am.open(assetFile).bufferedReader().use {
                iTextAssetMan.getText(it.readText())
            }
        }.start()
    }

}