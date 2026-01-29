## Button
Android Studio implementation<br>

## Emoticon
Data type<br>

## EmoticonButton
EmoticonButton <-"is-a"- Button<br>
EmoticonButton -"has-a"-> Emoticon<br>
EmoticonButton -1-"logs"-0..*- EmoticonButtonPress<br>

## EmoticonButtonPress 
Data type<br>

## EmoticonDailySummary
EmoticonDailySummary -1-"summarizes"-0..*- EmoticonButtonPress

## EmoticonAppUI
EmoticonAppUI <-"implements press"- EmoticonButtonPress<br>
EmoticonAppUI <-"implements view"- EmoticonDailySummary<br>