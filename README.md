# Documentation

The given project shows an easy way of normalizing input data, e.g. from a REST endpoint up to a message queue. It uses an aspect oriented approach to normalize parameters for a given method.

## Usage

1. Implement the necessary `Normalizer`, examples are given for `Person` and `Vehicle`
2. Annotate the method to normalize with the `@Normalize` annotation
3. Run the application