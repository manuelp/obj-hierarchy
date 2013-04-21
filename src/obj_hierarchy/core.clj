(ns obj-hierarchy.core)

(defn- get-bases 
  "A bases seq of an object has this structure: (superclass, interfaces...)"
  [x]
  (bases (class x)))

(defn- next-bases 
  "Get the next bases from a given one. It just take the superclass element and return its `bases`."
  [b]
  (bases (first b)))

(defn class-hierarchy 
  "Build a sequence with the object classes and interfaces hierarchy, up until `Object`.
  
  This hierarchy has this form:
  - object's class
  - superclass and object interfaces
  - super-superclass and superclass interfaces
  - ..."
  [x]
  (loop [b (get-bases x)
         res [(seq [(class x)])]]
    (if (= Object (first b))
      (conj res b)
      (recur (next-bases b) (conj res b)))))


(defn- build-el 
  "Given a couple of seqs in this form: 
  
  - e1: (:c :isb1 :isb2)
  - e2: (:sc :ic1 :ic1)
  
  Returns a sequence in this form: (:c :ic1 :ic2)"
  [e1 e2]
  (flatten (conj [] (first e1) (rest e2))))

(defn transform 
  "Transform an object hierarchy built by `class-hierarchy` so that each element
  is (class, interfaces) instead of (superclass, interfaces of the subclass)."
  [h]
  (loop [e (first h)
         r (next h)
         acc []]
    (if (nil? r)
      (seq (conj acc (seq [(first e)])))
      (recur (first r) 
             (next r) 
             (conj acc (build-el e (fnext r)))))))

