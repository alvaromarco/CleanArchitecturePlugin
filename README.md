# CleanArchitecturePlugin
<h1>Android Studio plugin generate clean architecture project to make develop easier.</h1>
This plugin is based in MVP pattern and Repository Pattern. You can create a entity main packages (data, domain and view) only write the name about the new entity.<br><br>
The core about this plugin its build on Retrofit 2 and Dagger 2. The main structure generated included @Inject about Dagger2 and Adapter about Retrofit 2 Adapter, its very recommened add Retrofit 2 and Dagger 2 libraries in your gradle. <br> 
Activity Android is based in AppCompatActivity to make it easier the compability with Android elements, also Fragment Android is based in support v4. <br>
If you select entity with presenter, create a presenter reference in view element and is required implements the interface to connect presenter and the corresponding view. <br> 
To works presenter you need add UseCase in package domain and create a new reference in presenter to works with it. <br>

The following features are available:
<li> Create entity with activity or fragment based in basic architecture or your basic architecture.</li>
<li> Any activity or fragment included a layout with simple RelativeLayout root element.
<li> Create entity activity or fragment with presenter.</li>
<li> Create entity adapter based in RecyclerView </li>

<br><b>Instructions</b><br>
<ol>
<li> <b>VERY IMPORTANT</b> copy all templates (/resources/templates) into AndroidStudioConfig/config/fileTemplates. <a href="http://tools.android.com/tech-docs/configuration">How to know the Android Studio Config folder</a>
<li> Its recommended for ths plugin add in your gradle the libraries Retrofit 2 and Dagger 2. 
<li> <b>Select directory where you create a new entity.</b></li>
<li> Right click and select <i> Clean Architecture Generator </i> or click in plugin icon.</li>
<li> Write the entity name. Please. the first letter about entity in uppercase.</li>
<li> Select activity or fragment. Only one </li>
<li> Select if you want with presenter and adapter </li>
<li> Click <i>Confirm</i> button and wait to create the entity</li>
</ol>
<br><br>

<h2>Architecture</h2>
<h3>All packages and class that you can create </h3>
<img src="https://github.com/alvaromarco/CleanArchitecturePlugin/blob/master/images/architecture_example.png" width="100%">

<h2>Plugin in Android Studio </h2>
<img src="https://github.com/alvaromarco/CleanArchitecturePlugin/blob/master/images/snapshot_1.png" width="100%">
<br><br>
<img src="https://github.com/alvaromarco/CleanArchitecturePlugin/blob/master/images/snapshot_2.png" width="100%">

<h2>Example Click Button</h2>
<h3>Show class and methods when you click in a button</h3>
<img src="https://github.com/alvaromarco/CleanArchitecturePlugin/blob/master/images/example_click_button.png" width="100%">

More information and screenshots: <a href="https://plugins.jetbrains.com/plugin/8504?pr=">Plugin reference</a>

alvaromarco 
