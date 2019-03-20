# Outline

### Purpose of Talk

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
- pure functions + data at the core (~99% of your code)
- side effects get pushed to the edge of the system (~1% of your code)
- basically it is an opinionated framework for writing SPAs

### Why re-frame?
- lots of good documentation and examples
- large community (relatively speaking)
- excellent performance (most CLJS + React.js combinations are)

### How does re-frame work?
- show the diagram in the README

- Benefits of this approach

- What happens as the app scales?
  - single source of state huge reduction of bugs
  - Subscriptions can get complex. But not unweildy
  - Tip: components should always be dead simple; do not do any data manipulation in a component

### Demos
- Simple app: counter
- add list of things
- make list searchable
-
- TODO: need to show a side effect / AJAX
