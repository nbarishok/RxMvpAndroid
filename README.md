# RxMvpAndroid
Sample to show how MVP, Clean Architecture and RxJava can help you in building Android App.

Here one can see yet another example of building an Android Application with help of MVP, Clean Architecture and RxJava.

Project contains of two submodules:
<ul>
  <li>core: base classes</li>
  <li>weather: sample app, displaying current temperature in Moscow based on <a href="http://openweathermap.org/api">OpenWeatherMap api</a> (add valid api-key in build.gradle to see the results)</li>
</ul>

<ul>Also here you can find seceral useful techinques:
<li>
saving presenter during configuration change / low - memory. This techinque is implemented using Dagger2 with its custom scopes + fragment-lifecycle awareness.
</li>
<li>
improved communication  between view and presenter. Details can be found in <a href="https://medium.com/@nbarishok/on-communication-between-v-and-p-in-android-mvp-16caf773e1a5#.hfuq2ddex">the article</a>
</li>
</ul>

_WORK IN PROGRESS_

NOW IN PROGRESS:
<ol>
<li>Handling navigation with MVP and Communication Bus</li>
</ol>

TODO:
<ol>
<li>proper testing</li>
<li>further architecture improvement</li>
<li>cover common (but interestring in terms of implementation in mvp) use cases during app development : services interaction, lists, dialogs etc.</li>
</ol>
