package com.example.fetchproduct.query

import com.shopify.buy3.Storefront

object Query {

    fun getAllProducts(
        cursor: String,
        sortby_key: Storefront.ProductSortKeys?,
        direction: Boolean,
        number: Int,
        list_currency: List<Storefront.CurrencyCode>
    ): Storefront.QueryRootQuery {
        val shoppro: Storefront.QueryRootQuery.ProductsArgumentsDefinition
        if (cursor == "nocursor") {
            if (sortby_key != null) {
                shoppro = Storefront.QueryRootQuery.ProductsArgumentsDefinition { args ->
                    args.first(number).sortKey(sortby_key).reverse(direction)
                }
            } else {
                shoppro = Storefront.QueryRootQuery.ProductsArgumentsDefinition { args ->
                    args.first(number).reverse(direction)
                }
            }
        } else {
            if (sortby_key != null) {
                shoppro = Storefront.QueryRootQuery.ProductsArgumentsDefinition { args ->
                    args.first(number).after(cursor).sortKey(sortby_key).reverse(direction)
                }
            } else {
                shoppro = Storefront.QueryRootQuery.ProductsArgumentsDefinition { args ->
                    args.first(number).after(cursor).reverse(direction)
                }
            }
        }
        return Storefront.query { root ->
            root.products(
                shoppro,
                productDefinition(list_currency)
            )
        }
    }

    fun productDefinition(list_currency: List<Storefront.CurrencyCode>): Storefront.ProductConnectionQueryDefinition {
        return Storefront.ProductConnectionQueryDefinition { productdata ->
            productdata
                .edges { edges ->
                    edges
                        .cursor()
                        .node { node ->
                            node
                                .handle()
                                .title()
                                .images({ img -> img.first(10) }
                                ) { imag ->
                                    imag.edges { imgedge ->
                                        imgedge
                                            .node { imgnode ->
                                                imgnode
                                                    .originalSrc()
                                                    .transformedSrc { t ->
                                                        t
                                                            .maxWidth(600)
                                                            .maxHeight(600)
                                                    }
                                            }
                                    }
                                }
                                .media({ m -> m.first(10) }) { me ->
                                    me.edges { e ->
                                        e.node { n ->
                                            n.onMediaImage { media ->
                                                media.previewImage { p ->
                                                    p.originalSrc()
                                                }
                                            }
                                                .onExternalVideo { _queryBuilder ->
                                                    _queryBuilder.embeddedUrl()
                                                        .previewImage {
                                                            it.originalSrc()
                                                        }
                                                }
                                                .onVideo(Storefront.VideoQueryDefinition {
                                                    it.previewImage {
                                                        it.originalSrc()
                                                    }.sources { it ->
                                                        it.url()
                                                    }
                                                })
                                                .onModel3d { md ->
                                                    md
                                                        .sources { s -> s.url() }
                                                        .previewImage { p -> p.originalSrc() }
                                                }
                                        }
                                    }
                                }
                                .availableForSale()
                                .descriptionHtml()
                                .description()
                                .tags()
                                .vendor()
                                .handle()
                                .totalInventory()
                                .variants({ args ->
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
                                                            { arg ->
                                                                arg.first(25).presentmentCurrencies(
                                                                    list_currency
                                                                )
                                                            }
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
                                }
                        }
                }
                .pageInfo(Storefront.PageInfoQueryDefinition { it.hasNextPage() }
                )
        }
    }

}