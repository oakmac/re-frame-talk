# Outline

### Purpose of Talk
- Using ClojureScript with re-frame is an excellent choice for building Single Page Applications
- Teach the basic concepts behind re-frame

### About Me

### Brief Introduction to ClojureScript
- many language choices when targeting JS: Clojure is excellent choice
- functional language with focus on practicality
- stable core
- build tools part of the language
- interop is easy; no restrictions
- flexible syntax that changes over time as needed

### Brief Introduction to React.js
- declarative DOM / HTML; uses a virtual DOM to perform updates
- component-based
- Virtual DOM approach is very popular; taking web development by storm last few years
  - Vue.js
  - Elm Architecture
  - many others taking similar approach

### State Management with React.js
- components can be like pure functions and take arguments (called "props")
- components can have local state (called "state")
- React.js community quickly converged on managing global state uniformly
  - Redux
  - other frameworks have also converged on this same pattern

### What is Reagent?
- ClojureScript wrapper around React.js
- re-renders components when an atom changes
- uses hiccups syntax
- very efficient; takes advantage of CLJS persistent data structures
- unopinionated about how to manage state or update atoms
  - how many atoms should I have?
  - how should I update them?
  - people have different answers / approaches to these questions

### What is re-frame?
- state management system built on top of Reagent
- pure functions + data at the core (~98% of your code)
- side effects get pushed to the edge of the system (~2% of your code)
- basically it is an opinionated framework for writing SPAs

### Why re-frame?
- lots of good documentation and examples
- large community (relatively speaking)
- excellent performance (most CLJS + React.js combinations are)
- scales well as your app gets more complex

### How does re-frame work?
- show the diagram in the README
- 1) dispatch Event - just some data (a vector)
- 2) Event Handlers receive the event
  - must be a pure function
  - receives the current state of the world (called "coeffects")
  - calculates the "next state" of the world
  - 99% of the time this is just updating the app-db
- 3) Optionally perform some side effect as a result of the new "coeffects" map
- 4) Subscription are "listening" to app-db state
  - trigger in response to a change
  - extract some data out of app-db
- 5) Components render based on new subscription values
  - React.js handles the rest

### Benefits of re-frame
- stable, consistent syntax everywhere (CLJS benefit)
- single source of state: huge reduction in complexity distribution
- most of the code you write is either 1) data or 2) pure function
- side effects get pushed to the "edge" of the application
- events and subscription de-couple "update logic" from "view logic"
  - making a change in one does not necessarily require a corresponding change elsewhere
- easy to manage lots of events
  - they are just data
  - you can namespace them
- basically never need to worry about performance due to CLJS persistent data structures

### Downsides
- need to learn re-frame vocabulary / concepts
- subscriptions can get complex
- app-db structure can be hard to change
  - usually requires changing both Event Handlers and Subscriptions when app-db structure changes
  - tip: think about the best structure for your data up front
-

### Tips / Advice
- components should always be dead simple
  - do not do any data manipulation in a component
  - Subscriptions should contain that logic
- namespace your events
  - [:accounts-page/init]
  - [:header/select-tab "HOMEPAGE"]
  - etc
- use consistent namespace organization
  - either separate Subscription, Events, Components
  - or put them together in a single namespace
- CapitalCase for components
  - I like this convention

### Demos
- Simple app: counter
- TODO: add list of things
- TODO: make list searchable
- TODO: need to show a side effect / AJAX
