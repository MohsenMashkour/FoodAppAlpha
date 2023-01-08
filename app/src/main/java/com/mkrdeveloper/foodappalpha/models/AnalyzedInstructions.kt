package com.mkrdeveloper.foodappalpha.models

data class AnalyzedInstruction(
  val name: String,
  val steps: List<Step>
)