# Kotlin Mvp Espresso
This example describes how use Espresso in Android-Kotlin Using MVP, Dagger2, Retrofit


**Espresso** is testing framework, provided by the Android Testing Support Library, provides APIs for writing UI tests to simulate user interactions. 
The steps for [setup Espresso ](https://google.github.io/android-testing-support-library/docs/espresso/setup/) is the same steps if you use kotlin or java.


## Espresso main components: 
* **Espresso :** (`onView()` and `onData()`) Finds the view by using ViewMatchers.<br />
* **ViewMatchers :** (`withId()`) Which can pass into the onView method to locate a view within the current view hierarchy.<br />
* **ViewActions :** (`perform()`) Performs ViewActions for example: `click()`. <br />
* **ViewAssertions :** (`check()`) Validate the state of the currently selected view for example: `isDisplayed()`. 
 
###### Example: 
```
Espresso.onView(ViewMatchers.withId(R.id.btn_submit)) 
.perform(ViewActions.click()) 
.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
```

## Expresso packages: 
* Espresso-Core 
* Espresso-idling-resource 
* Espresso-Contrib (RecyclerViewActions) 
* Espresso-intents 
* ~~Espresso-web~~ `Not included in this project`


**Espresso-Core :** <br/>
Contains core and basic View matchers, actions, and assertions. 
###### Example: 
###### TextView 
```
// check if tv_empty_data displayed on current screen  and contain no_data_available text
onView(withId(R.id.tv_empty_data)).check(matches(isDisplayed())) 
onView(withId(R.id.tv_empty_data)).check(matches(withText(R.string.no_data_available))) 
```
###### ReceycleView 
```
onView(withId(R.id.rv_repositories_list)).check (matches (not (isDisplayed ()))) 
```
`matches (not (isDisplayed ())` will be true if the view is *INVISIBLE* or *GONE* you can check the visibility 
```
onView(withId(R.id.rv_repositories_list)).check(matches(withEffectiveVisibility(Visibility.GONE))); 
```
> Be careful with the difference between asserting that a view is not displayed and asserting that a view is not present in the view hierarchy. 

**`doesNotExist()`** Mean the view is not present in the view hierarchy, and this is different than `not(Displayed())` 
**`not(Displayed())`** Mean the view is in the view hierarchy but the visiblility is (INVISIBLE or GONE) or the view not displayed now in screen.  
###### Example Snackbar: 
```
onView(allOf(withId(android.support.design.R.id.snackbar_text), 
              withText("Please enter github account name")))
                .check(doesNotExist()); 
```
` allOf `: used when looking through the various attributes of the views 
```
onView(allOf(withId(R.id.but_show_repositories), 
              withText("Show Repositories List"), 
              isDisplayed()))
                .perform(click()) 
 ```
 
**Espresso-idling-resource:** <br/>
Espresso waits until the UI is idle before it moves to the next operation. However, there are instances where applications perform background operations (such as communicating with web services) via non-standard means; for example: direct creation and management of threads. 
In such cases, you have to use Idling Resources for synchronization with background jobs. 

The ways to implement Idling Resources: 
* Counting running jobs 
* Querying state 
 
###### For example splash screen 
I created `SplashIdlingResource` to wait for 3 sec (splash delay time) then inform Espresso

```
class SplashIdlingResource(private val waitingTime: Long) : IdlingResource { 
    private val startTime: Long 
    private var resourceCallback: ResourceCallback? = null 
 
    init { 
        this.startTime = System.currentTimeMillis() 
    } 
 
    override fun getName(): String { 
        return SplashIdlingResource::class.java.name + ":" + waitingTime 
    } 
 
    override fun isIdleNow(): Boolean { 
        val elapsed = System.currentTimeMillis() - startTime 
        val idle = elapsed >= waitingTime 
        if (idle) { 
            resourceCallback!!.onTransitionToIdle() 
        } 
        return idle 
    } 
 
    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) { 
        this.resourceCallback = resourceCallback 
    } 
} 
```
Register it `@Before` and unRgigster it `@After `
 
**Espresso-Contrib: (RecyclerViewActions)**
External contributions that contain DatePicker, RecyclerView and Drawer actions, accessibility checks, and CountingIdlingResource. 
In this example I will use  ` RecyclerViewActions ` to scroll to a specific item.
With out RecyclerviewActions you can only test the visible items on screen to test invisble item you have to scroll to position 
```
onView(withId(R.id.rv_repositories_list))
    .perform(RecyclerViewActions.scrollToPosition<RepositoriesListAdapter.RepositoryItemViewHolder>(9)) 
    
onView( 
        allOf(withId(R.id.tv_repository_name),  withText("Flickr"), // we can set tag and test it withTagValue 
                isDisplayed())).check(matches(withText("Flickr"))) 
 ```
 
**Espresso-intents:**
Extension to validate and stub intents for hermetic testing. 
Here you can mock the intent and to be sure if app navigate to correct intent or not.

To use Espresso-intents: 
* Use the IntentsTestRule instead of ActivityTestRule. 
* Or init `Intents` at `@Before` and release it at `@After` 
 
 ```
 // is an equivalent of verify(mock, times(1)) in Mockito 
Intents.intended(IntentMatchers.hasComponent(RepositoriesListActivity::class.java.name)) 
 ```

*Note* 
- It is recommended to turn of the animation on the Android device which is used for testing. Animations might confusing Espressos check for ideling resources 
- Espresso tests must be placed in the app/src/androidTest folder. 
 
## Refrence: 
* [https://codelabs.developers.google.com/codelabs/android-testing/index.html?index=..%2F..%2Findex#0 ](https://codelabs.developers.google.com/codelabs/android-testing/index.html?index=..%2F..%2Findex#0 )
* [https://google.github.io/android-testing-support-library/docs/espresso/cheatsheet/index.html](https://google.github.io/android-testing-support-library/docs/espresso/cheatsheet/index.html)
* [http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html ](http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html )
* [https://github.com/googlesamples/android-architecture](https://github.com/googlesamples/android-architecture)
