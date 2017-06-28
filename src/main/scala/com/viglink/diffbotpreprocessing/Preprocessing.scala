package com.viglink.diffbotpreprocessing

import java.io.{File, PrintWriter}

import org.jsoup.Jsoup

import scala.collection.JavaConversions._

object Preprocessing {


  def htmlFile2BratFile(fileLocation: File, saveLocation: File) = {

    val validTags = Set("a", "span", "img", "meta", "h4", "p", "input", "h3", "h2")
    var seenTags:Set[String] = Set()

    val html = Jsoup.parse(fileLocation, "UTF-8")

    val tags = html.select("*")

    val pr = new PrintWriter(saveLocation)

    tags.foreach { tag =>

      val numTagLines = tag.outerHtml().split("\n").toList.size()

      if (validTags.contains(tag.tagName()) && numTagLines < 5) {
        //println("=========================new tag=========================")
//        println(tag)
//        println(tag.tagName())
//        println(tag.outerHtml().split("\n").toList.size())

        if(!seenTags.contains(tag.toString)){
          println("=========================new tag=========================")

          println(tag)
          pr.println(tag.toString)
        }

        seenTags+=tag.toString

        println(seenTags.size())
      }

    }

    pr.close()
  }


}


object Test extends App {

  Preprocessing.htmlFile2BratFile(new File("amazon.html"), new File("amazon_brat.txt"))


}