package com.example.fetchproduct.query

import com.shopify.buy3.Storefront

object Query {

    val shopDetails: Storefront.QueryRootQuery
        get() = Storefront.query { q ->
            q
                .shop { shop ->
                    shop.name()

                        .paymentSettings { pay ->
                            pay.enabledPresentmentCurrencies().currencyCode()
                        }

                }
                .products (getData(), productDefinition())

        }

fun getData():Storefront.QueryRootQuery.ProductsArgumentsDefinition{
    val definition: Storefront.QueryRootQuery.ProductsArgumentsDefinition
    definition = Storefront.QueryRootQuery.ProductsArgumentsDefinition { args ->
        args.first(10).reverse(true)
    }
    return definition
}

    fun productDefinition(): Storefront.ProductConnectionQueryDefinition {
        return Storefront.ProductConnectionQueryDefinition { productdata ->
            productdata
                .edges { edges ->
                    edges
                        .cursor()
                        .node { node ->
                            node
                                .title()

                               /* .variants({ args ->
                                    args
                                        .first(120)
                                }
                                ) { variant ->
                                    variant
                                        .edges { variantEdgeQuery ->
                                            variantEdgeQuery
                                                .node { productVariantQuery ->
                                                    productVariantQuery
                                                        .priceV2 { price ->
                                                            price.amount().currencyCode()
                                                        }
//                                                        .storeAvailability({ args -> args.first(20) },
//                                                            { storeAvail ->
//                                                                storeAvail.edges({ storeAvailEdges ->
//                                                                    storeAvailEdges.node({
//                                                                        it.available()
//                                                                        it.pickUpTime()
//                                                                        it.location {
//                                                                            it.name()
//                                                                            it.address {
//                                                                                it.address1()
//                                                                                it.address2()
//                                                                                it.city()
//                                                                                it.country()
//                                                                                it.province()
//                                                                                it.zip()
//                                                                                it.phone()
//                                                                            }
//                                                                        }
//                                                                    })
//                                                                })
//                                                            })
                                                        .price()
                                                        .title()
                                                        .quantityAvailable()
                                                        .presentmentPrices(
                                                            *//*{ arg ->
                                                                arg.first(25).presentmentCurrencies(
                                                                    list_currency
                                                                )
                                                            }*//*
                                                        ) { price ->
                                                            price.edges { e ->
                                                                e.cursor().node { n ->
                                                                    n.price { p ->
                                                                        p.amount()
                                                                            .currencyCode()
                                                                    }.compareAtPrice { cp ->
                                                                        cp.amount()
                                                                            .currencyCode()
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        .selectedOptions { select ->
                                                            select.name().value()
                                                        }
                                                        .compareAtPriceV2 { compare ->
                                                            compare.amount().currencyCode()
                                                        }
                                                        .compareAtPrice()
                                                        .currentlyNotInStock()
                                                        .image { image ->
                                                            image.transformedSrc { tr ->
                                                                tr.maxHeight(
                                                                    600
                                                                ).maxWidth(600)
                                                            }.originalSrc()
                                                        }
                                                        .availableForSale()
                                                        .sku()
                                                }
                                        }
                                }
                                .onlineStoreUrl()
                                .options { op ->
                                    op.name()
                                        .values()
                                }*/
                        }
                }
                .pageInfo(Storefront.PageInfoQueryDefinition { it.hasNextPage() }
                )
        }
    }

}