# RxMvpAndroid
Sample to show how MVP, Clean Architecture and RxJava can help you in building Android App.

<h3> Description </h3>
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
<li>
navigation support, post about implementation details is in <a href="https://medium.com/@nbarishok/on-navigation-in-android-mvp-d26065586dcd#.oilhpvcr1">this article</a>
</li>
<li>
Basic approach for manual  ViewState management is introduced, and the main reason for that - the fact that using onSaveInstanceState can lead to data loss
</li>
<li>
Also dependency 'dialogwrapper.aar' appeared, it is a library to abstract away complexities of working with dialogs. Sources with the base implementation can be found <a href="https://github.com/nbarishok/DialogFragmentTinyWrapper"> here</a>. This approach for dealing with dialogs works for me.
</li>
</ul>
