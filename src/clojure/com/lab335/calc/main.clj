(ns com.lab335.calc.main
  (:use [neko.activity :only [defactivity set-content-view!]]
        [neko.threading :only [on-ui]]
        [neko.notify]
        [neko.ui :only [make-ui]])
  (:import android.widget.EditText
           android.widget.TextView))

(declare ^EditText left
         ^EditText right
         ^TextView result)

(defn add []
  (let [left-value (str (.getText left))
        right-value (str (.getText right))
        result-value (+ (Integer/parseInt left-value) (Integer/parseInt right-value))]
    (.setText result (str result-value))))
    
(defactivity com.lab335.calc.MainActivity
  :def a
  :on-create
  (fn [this bundle]
    (on-ui
     (set-content-view!
      a
      (make-ui [:linear-layout {:orientation :vertical}
                [:edit-text {:def `left :hint "Left value"}]
                [:text-view {:text "+"}]
                [:edit-text {:def `right :hint "Right value"}]
                [:button {:text "Add"
                          :on-click (fn [_] (add))}]
                [:text-view {:def `result
                             :text "0"}]])))))
