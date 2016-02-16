# RxMvpAndroid
Sample to show how MVP, Clean Architecture and RxJava can help you in building Android App.

Here one can see yet another example of building an Android Application with help of MVP, Clean Architecture and RxJava.

Project contains of two submodules:
<ul>
  <li>core: base classes</li>
  <li>weather: sample app, displaying current temperature in Moscow based on http://openweathermap.org/api (add valid api-key in build.gradle to see the results)</li>
</ul>

<ul>Also here you can find seceral useful techinques:
<li>
saving presenter during configuration change / low - memory. This techinque is implemented using Dagger2 with its custom scopes + fragment-lifecycle awareness.
</li>
<li>
(raw) eliminationg nasty null - checks in presenter + optimizing presenter for it not to lose request results
</li>
</ul>

_WORK IN PROGRESS_

TODO:
<ol>
<li>proper testing</li>
<li>how presenter saving occurs (description)</li>
</ol>
