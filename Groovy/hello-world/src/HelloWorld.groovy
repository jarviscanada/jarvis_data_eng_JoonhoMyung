class HelloWorld {
    static void main(String[] args){
        Person johnDoe = new Person()
        johnDoe.setFirstname("John")
        johnDoe.setLastname("Doe")
        johnDoe.setAge(40)

        println johnDoe.getFullName()
        println johnDoe.getAge()

        if (johnDoe.getAge() >= 45 && johnDoe.getAge() <= 65) {
            println johnDoe.getFullName() + " is middle-aged"
        } else {
            println johnDoe.getFullName() + " is " + johnDoe.getAge() + " years old"
        }

        def persons = [johnDoe, new Person(firstname: "Mary", lastname: "Hill", age: 40)]

        for (Person p : persons)
            println p.getFullName()
    }
}
