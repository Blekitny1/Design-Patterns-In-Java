package Flyweight;

//
//import com.google.common.base.Function;
//

class User
{
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}
//
//class User2
//{
//    static List<String> strings = new ArrayList<>();
//    private int[] names;
//
//    public User2(String fullName)
//    {
//        Function<String, Integer> getOrAdd = (String s) -> {
//            int idx = strings.indexOf(s);
//            if (idx != -1) return idx;
//            else {
//                strings.add(s);
//                return strings.size() - 1;
//            }
//        };
//
//        names = Arrays.stream(fullName.split(" "))
//                .mapToInt(s -> getOrAdd.apply(s)).toArray();
//    }
//
//    public String getFullName()
//    {
//        return Arrays.stream(names).mapToObj(i -> strings.get(i))
//                .collect(Collectors.joining(","));
//    }
//}
//
class UsersFlyweight
{
    public static void main(String[] args) {
        User user = new User("John Smith");
        User user1 = new User("Jane Smith");

        // have "Smith" in common
    }
}