# Outline

- Purpose of Talk
- About Me
- Brief Introduction to ClojureScript
  - many language choices when targeting JS: Clojure is excellent choice
  - functional language with focus on practicality
  - stable core
  - build tools part of the language
  - interop is easy; no restrictions
  - flexible syntax that changes over time as needed

- Brief Introduction to React.js
  - declarative DOM / HTML; use a virtual DOM to perform updates
  - component-based
  - Virtual DOM approach is very popular; taking web development by storm last few years
    - Vue.js
    - Elm Architecture
    - many others taking similar approach

- State Management with React.js
  - components can be like pure functions and take arguments (called "props")
  - components can have local state (called "state")
  - React.js community quickly converged on managing global state uniformly
    - Redux
    - other frameworks have also converged on this same pattern

- What is Reagent?
  - ClojureScript wrapper around React.js
  - explain atoms and then reagent/atom
  - hiccups syntax

- What is re-frame?
  - state management on top of Reagent
  - basically it is an opinionated framework for writing large-scale SPAs
  - it works great! largely because of CLJS defaults

- How does re-frame work?
  - App-db (think: spreadsheet)
  - Render Loop
  - Subscriptions
  - Events
  - Coeffects / effects
  - Components

- Benefits of this approach

- What happens as the app scales?
  - single source of state huge reduction of bugs
  - Subscriptions can get complex. But not unweildy
  - Tip: components should always be dead simple; do not do any data manipulation in a component

### Demos
- Simple app: counter?
- More complex: todo?
- More complex: show same data multiple ways via subscriptions