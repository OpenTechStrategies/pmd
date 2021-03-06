/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.properties;

import java.util.List;
import java.util.Map;

import net.sourceforge.pmd.properties.PropertyBuilder.GenericCollectionPropertyBuilder;
import net.sourceforge.pmd.properties.PropertyBuilder.GenericPropertyBuilder;
import net.sourceforge.pmd.properties.constraints.NumericConstraints;
import net.sourceforge.pmd.properties.constraints.PropertyConstraint;

//@formatter:off
/**
 * Provides factory methods for common property types.
 * Note: from 7.0.0 on, this will be the only way to
 * build property descriptors. Concrete property classes
 * and their constructors/ builders will be gradually
 * deprecated before 7.0.0.
 *
 * <h1>Usage</h1>
 *
 * Properties are a way to make your rule configurable by
 * letting a user fill in some config value in their
 * ruleset XML.
 *
 * As a rule developer, to declare a property on your rule, you
 * must:
 * <ul>
 *     <li>Build a {@link PropertyDescriptor} using one of
 *     the factory methods of this class, and providing the
 *     {@linkplain PropertyBuilder builder} with the required
 *     info.
 *     <li>Define the property on your rule using {@link PropertySource#definePropertyDescriptor(PropertyDescriptor)}.
 *     This should be done in your rule's constructor.
 * </ul>
 *
 * You can then retrieve the value configured by the user in your
 * rule using {@link PropertySource#getProperty(PropertyDescriptor)}.
 *
 * <h1>Example</h1>
 *
 * <pre>
 * class MyRule {
 *   // The property descriptor may be static, it can be shared across threads.
 *   private static final {@link PropertyDescriptor}&lt;Integer> myIntProperty
 *     = PropertyFactory.{@linkplain #intProperty(String) intProperty}("myIntProperty")
 *                      .{@linkplain PropertyBuilder#desc(String) desc}("This is my property")
 *                      .{@linkplain PropertyBuilder#defaultValue(Object) defaultValue}(3)
 *                      .{@linkplain PropertyBuilder#require(PropertyConstraint) require}(inRange(0, 100))   // constraints are checked before the rule is run
 *                      .{@linkplain PropertyBuilder#build() build}();
 *
 *   // ..
 *
 *   public MyRule() {
 *     {@linkplain PropertySource#definePropertyDescriptor(PropertyDescriptor) definePropertyDescriptor}(myIntProperty);
 *   }
 *
 *     // ... somewhere in the rule
 *
 *     int myPropertyValue = {@linkplain PropertySource#getProperty(PropertyDescriptor) getProperty(myIntProperty)};
 *     // use it.
 *
 * }
 * </pre>
 *
 *
 * @author Clément Fournier
 * @since 6.10.0
 */
//@formatter:on
public final class PropertyFactory {

    private PropertyFactory() {

    }


    /**
     * Returns a builder for an integer property. The property descriptor
     * will by default accept any value conforming to the format specified
     * by {@link Integer#parseInt(String)}, e.g. {@code 1234} or {@code -123}.
     * Acceptable values may be further refined by {@linkplain PropertyBuilder#require(PropertyConstraint) adding constraints}.
     * The class {@link NumericConstraints} provides some useful ready-made constraints
     * for that purpose.
     *
     * @param name Name of the property to build
     *
     * @return A new builder
     *
     * @see NumericConstraints
     */
    public static GenericPropertyBuilder<Integer> intProperty(String name) {
        return new GenericPropertyBuilder<>(name, ValueParserConstants.INTEGER_PARSER, Integer.class);
    }


    public static GenericCollectionPropertyBuilder<Integer, List<Integer>> intListProperty(String name) {
        return intProperty(name).toList().delim(MultiValuePropertyDescriptor.DEFAULT_NUMERIC_DELIMITER);
    }


    public static GenericPropertyBuilder<Double> doubleProperty(String name) {
        return new GenericPropertyBuilder<>(name, ValueParserConstants.DOUBLE_PARSER, Double.class);
    }


    public static GenericCollectionPropertyBuilder<Double, List<Double>> doubleListProperty(String name) {
        return doubleProperty(name).toList().delim(MultiValuePropertyDescriptor.DEFAULT_NUMERIC_DELIMITER);
    }


    public static GenericPropertyBuilder<String> stringProperty(String name) {
        return new GenericPropertyBuilder<>(name, ValueParserConstants.STRING_PARSER, String.class);
    }


    public static GenericCollectionPropertyBuilder<String, List<String>> stringListProperty(String name) {
        return stringProperty(name).toList().delim(MultiValuePropertyDescriptor.DEFAULT_DELIMITER);
    }


    public static <T> GenericPropertyBuilder<T> enumProperty(String name, Map<String, T> nameToValue) {
        // TODO find solution to document the set of possible values
        // At best, map that requirement to a constraint (eg make parser return null if not found, and
        // add a non-null constraint with the right description.)
        return new GenericPropertyBuilder<>(name, ValueParserConstants.enumerationParser(nameToValue), (Class<T>) Object.class);
    }


    public static <T> GenericCollectionPropertyBuilder<T, List<T>> enumListProperty(String name, Map<String, T> nameToValue) {
        return enumProperty(name, nameToValue).toList();
    }


}
